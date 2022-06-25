package pe.com.nttdata.account.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pe.com.nttdata.account.model.document.Account;
import pe.com.nttdata.account.model.repository.AccountRepository;
import pe.com.nttdata.account.model.service.AccountService;
import pe.com.nttdata.account.util.SequenceGeneratorService;
import pe.com.nttdata.account.util.SequenceGeneratorServiceImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;

    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Flux<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Mono<Account> save(Account account) throws ExecutionException, InterruptedException {
        account.setId(sequenceGeneratorService.generateSequence(Account.SEQUENCE_NAME));
        account.setActive(true);
        account.setCreatedAt(new Date());
        account.setUpdatedAt(null);
        return accountRepository.save(account).onErrorResume(e -> {
            log.info(e.getMessage());
            return null;
        });
    }

    @Override
    public Mono<Account> update(Account account) {
        return accountRepository.findById(account.getId()).flatMap(acc -> {
            acc.setUpdatedAt(new Date());
            return accountRepository.save(acc);
        });
    }

    @Override
    public Mono<Account> delete(Long id) {
        return accountRepository.findById(id).flatMap(acc -> {
            acc.setActive(false);
            acc.setUpdatedAt(new Date());
            return accountRepository.save(acc);
        });
    }

    @Override
    public Mono<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Mono<Boolean> existById(Long id) {
        return accountRepository.existsById(id);
    }
}
