package pe.com.nttdata.account.type.model.service;

import pe.com.nttdata.account.type.model.document.TypeAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

public interface TypeAccountService {

    public Flux<TypeAccount> getAll();
    public Mono<TypeAccount> findById(Long id);
    public Mono<TypeAccount> save(TypeAccount typeAccount) throws ExecutionException, InterruptedException;
    public Mono<TypeAccount> update(TypeAccount typeAccount);
    public Mono<TypeAccount> delete(Long id);

}
