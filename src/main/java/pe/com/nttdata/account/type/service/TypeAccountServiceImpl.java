package pe.com.nttdata.account.type.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pe.com.nttdata.account.type.model.document.TypeAccount;
import pe.com.nttdata.account.type.model.repository.TypeAccountRepository;
import pe.com.nttdata.account.type.model.service.TypeAccountService;
import pe.com.nttdata.account.util.SequenceGeneratorService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class TypeAccountServiceImpl implements TypeAccountService {

    private static final Logger log = LoggerFactory.getLogger(TypeAccountServiceImpl.class);

    private final TypeAccountRepository typeAccountRepository;

    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Flux<TypeAccount> getAll() {
        return typeAccountRepository.findAll();
    }

    @Override
    public Mono<TypeAccount> findById(Long id) {
        return typeAccountRepository.findById(id);
    }

    @Override
    public Mono<TypeAccount> save(TypeAccount typeAccount) throws ExecutionException, InterruptedException {
        typeAccount.setId(sequenceGeneratorService.generateSequence(TypeAccount.SEQUENCE_NAME));
        typeAccount.setActive(true);
        typeAccount.setCreatedAt(new Date());
        typeAccount.setUpdatedAt(null);
        return typeAccountRepository.save(typeAccount);
    }

    @Override
    public Mono<TypeAccount> update(TypeAccount typeAccount) {
        return typeAccountRepository.findById(typeAccount.getId()).flatMap(t -> {
            t.setName(typeAccount.getName());
            t.setUpdatedAt(new Date());
            return typeAccountRepository.save(t);
        });
    }

    @Override
    public Mono<TypeAccount> delete(Long id) {
        return typeAccountRepository.findById(id).flatMap(t -> {
            t.setActive(false);
            t.setUpdatedAt(new Date());
            return typeAccountRepository.save(t);
        });
    }
}
