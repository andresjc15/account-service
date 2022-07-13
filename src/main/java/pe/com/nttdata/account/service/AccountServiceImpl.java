package pe.com.nttdata.account.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.nttdata.account.model.document.Account;
import pe.com.nttdata.account.model.repository.AccountRepository;
import pe.com.nttdata.account.model.service.AccountService;
import pe.com.nttdata.account.type.model.repository.TypeAccountRepository;
import pe.com.nttdata.account.util.SequenceGeneratorService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final TypeAccountRepository typeAccountRepository;

    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Flux<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Mono<Account> save(Account account) throws ExecutionException, InterruptedException {
        account.setId(sequenceGeneratorService.generateSequence(Account.SEQUENCE_NAME));
        return typeAccountRepository.findById(account.getTypeAccount().getId()).flatMap(type -> {
            account.setTypeAccount(type);
            account.setActive(true);
            account.setCreatedAt(new Date());
            account.setUpdatedAt(null);
            return accountRepository.save(account).doOnSuccess(obj -> {
                log.info("[ACCOUNT SAVED SUCCESSFULLY]: " + obj);
            });
        });
    }

    @Override
    public Mono<Account> update(Account account) {
        return accountRepository.findById(account.getId()).flatMap(acc -> {
            acc.setAmount(account.getAmount());
            acc.setUpdatedAt(new Date());
            return accountRepository.save(acc).doOnSuccess(obj -> {
                log.info("[ACCOUNT UPDATED SUCCESSFULLY]: " + obj);
            });
        });
    }

    @Override
    public Mono<Account> delete(Long id) {
        return accountRepository.findById(id).flatMap(acc -> {
            acc.setActive(false);
            acc.setUpdatedAt(new Date());
            return accountRepository.save(acc).doOnSuccess(obj -> {
                log.info("[ACCOUNT DELETED SUCCESSFULLY]: " + obj);
            });
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

    @Override
    public Mono<Account> saveSavingAccount(Account account) throws ExecutionException, InterruptedException {
        account.setId(sequenceGeneratorService.generateSequence(Account.SEQUENCE_NAME));
        log.info("[SAVING]");
        return typeAccountRepository.findById(1L).flatMap(type -> {
            account.setTypeAccount(type);
            account.setActive(true);
            account.setCreatedAt(new Date());
            account.setUpdatedAt(null);
            long  leftLimit = 999999999999L;
            long  rightLimit = 9999999999999L;
            long  generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
            System.out.println("Value: " + generatedLong);
            account.setNumberAccount(generatedLong);
            log.info("[SAVING OBJECT]: " + account.toString());
            return accountRepository.save(account);
        });
    }

    @Override
    public Mono<Account> saveCurrentAccount(Account account) throws ExecutionException, InterruptedException {
        account.setId(sequenceGeneratorService.generateSequence(Account.SEQUENCE_NAME));
        log.info("[SAVING]");
        return typeAccountRepository.findById(2L).flatMap(type -> {
            account.setTypeAccount(type);
            account.setActive(true);
            account.setCreatedAt(new Date());
            account.setUpdatedAt(null);
            log.info("[SAVING OBJECT]: " + account.toString());
            return accountRepository.save(account);
        });
    }

    @Override
    public Mono<Account> saveFixedTerm(Account account) throws ExecutionException, InterruptedException {
        account.setId(sequenceGeneratorService.generateSequence(Account.SEQUENCE_NAME));
        log.info("[SAVING]");
        return typeAccountRepository.findById(3L).flatMap(type -> {
            account.setTypeAccount(type);
            account.setActive(true);
            account.setCreatedAt(new Date());
            account.setUpdatedAt(null);
            log.info("[SAVING OBJECT]: " + account.toString());
            return accountRepository.save(account);
        });
    }

    @Override
    public Mono<Account> savePersonalCredit(Account account) throws ExecutionException, InterruptedException {
        account.setId(sequenceGeneratorService.generateSequence(Account.SEQUENCE_NAME));
        return typeAccountRepository.findById(4L).flatMap(type -> {
            account.setTypeAccount(type);
            account.setActive(true);
            account.setCreatedAt(new Date());
            account.setUpdatedAt(null);
            log.info("[SAVING OBJECT]: " + account.toString());
            return accountRepository.save(account);
        });
    }

    @Override
    public Mono<Account> saveBusinessCredit(Account account) throws ExecutionException, InterruptedException {
        account.setId(sequenceGeneratorService.generateSequence(Account.SEQUENCE_NAME));
        log.info("[SAVING]");
        return typeAccountRepository.findById(5L).flatMap(type -> {
            account.setTypeAccount(type);
            account.setActive(true);
            account.setCreatedAt(new Date());
            account.setUpdatedAt(null);
            log.info("[SAVING OBJECT]: " + account.toString());
            return accountRepository.save(account);
        });
    }

    @Override
    public Mono<Account> saveCreditCard(Account account) throws ExecutionException, InterruptedException {
        account.setId(sequenceGeneratorService.generateSequence(Account.SEQUENCE_NAME));
        log.info("[SAVING...]");
        return typeAccountRepository.findById(6L).flatMap(type -> {
            account.setTypeAccount(type);
            account.setActive(true);
            account.setCreatedAt(new Date());
            account.setUpdatedAt(null);
            log.info("[SAVING OBJECT]: " + account.toString());
            return accountRepository.save(account);
        });
    }
}
