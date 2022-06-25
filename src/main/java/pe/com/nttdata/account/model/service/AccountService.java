package pe.com.nttdata.account.model.service;

import pe.com.nttdata.account.model.document.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

public interface AccountService {

    Flux<Account> getAll();
    Mono<Account> save(Account account) throws ExecutionException, InterruptedException;
    Mono<Account> update(Account account);
    Mono<Account> delete(Long id);
    Mono<Account> findById(Long id);
    Mono<Boolean> existById(Long id);

}
