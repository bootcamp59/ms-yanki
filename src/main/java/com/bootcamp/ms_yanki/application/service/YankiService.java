package com.bootcamp.ms_yanki.application.service;

import com.bootcamp.ms_yanki.application.port.in.YankiUseCase;
import com.bootcamp.ms_yanki.application.port.out.DebitCardServicePort;
import com.bootcamp.ms_yanki.application.port.out.YankiEventPublisher;
import com.bootcamp.ms_yanki.application.port.out.YankiRepositoryPort;
import com.bootcamp.ms_yanki.domain.dto.Yankeo;
import com.bootcamp.ms_yanki.domain.model.Yanki;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class YankiService implements YankiUseCase {

    private final YankiRepositoryPort port;
    private final DebitCardServicePort debitCardServicePort;
    private final YankiEventPublisher eventPublisher;

    @Override
    public Mono<Yanki> create(Yanki model) {
        return port.create(model);
    }

    @Override
    public Flux<Yanki> findAll() {
        return port.finAll();
    }

    @Override
    public Mono<Yankeo> transfer(Yankeo transfer) {

        return port.findByPhones(List.of(transfer.getFromPhone(), transfer.getToPhone()))
            .collectMap(Yanki::getPhone)
            .flatMap( wallets -> {
                Yanki origen = wallets.get(transfer.getFromPhone());
                Yanki destino = wallets.get(transfer.getToPhone());

                // Validación de existencia
                validateWalletExistence(origen, transfer.getFromPhone());
                validateWalletExistence(destino, transfer.getToPhone());

                boolean origenTieneDebit = StringUtils.hasText(origen.getDebitCardNumber());
                boolean destinoTieneDebit = StringUtils.hasText(destino.getDebitCardNumber());

                //validar si tiene debitcard asociada para saber de donde se sacara el dinero para la transferencia
                if(!origenTieneDebit){
                    var balanceOrigen = Optional.ofNullable(origen.getBalance()).orElse(BigDecimal.ZERO);
                    var balanceDestino = Optional.ofNullable(destino.getBalance()).orElse(BigDecimal.ZERO);
                    //validar si el origen tiene saldo solo en su wallet
                    if(balanceOrigen.compareTo(transfer.getAmount()) < 0){
                        return Mono.error(new RuntimeException("Wallet No cuenta con saldo suficiente para realizar la operacion"));
                    } else {
                        //aqui se hara la transferencia(deposito) con el saldo de la wallet
                        // Si el destino tampoco tiene tarjeta: es una transferencia wallet -> wallet
                        if(!destinoTieneDebit){
                            origen.setBalance(balanceOrigen.subtract(transfer.getAmount()));
                            destino.setBalance(balanceDestino.add(transfer.getAmount()));
                        }
                    }
                }

                // Usamos Mono.zip para asegurarnos de que ambas operaciones sean atómicas
                return Mono.zip(
                        port.updateBalance(destino),
                        port.updateBalance(origen)
                    )
                    .thenReturn(transfer)
                    .onErrorResume(e -> Mono.error(new RuntimeException("Error en la actualización de saldos: " + e.getMessage())));


                // validar si el origen tiene saldo en su debitcard asociado a su cuenta principal



                //validar si el wallet destino tiene debitcard  para transferirle a su cuenta principal asociada


                // si no tiene debitcard se le depesitara a su wallet






            });
    }

    @Override
    public Mono<Void> linkDebitCard(String phone, String debitCardNumber) {
        return port.findByPhone(phone)
            .flatMap(yanki -> {
                yanki.setDebitCardNumber(debitCardNumber);
                yanki.setAssociationStatus("PENDING");
                return port.create(yanki)
                    .doOnSuccess(eventPublisher::publishLinkDebitCard);
            })
            .then();
    }

    private void validateWalletExistence(Yanki wallet, String phone) {
        if (wallet == null) {
            throw new RuntimeException("El wallet con el teléfono [" + phone + "] no existe");
        }
    }
}
