package pe.com.nttdata.account.api;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pe.com.nttdata.account.client.customer.model.EnterpriseCustomer;
import pe.com.nttdata.account.client.customer.model.service.EnterpriseCostumerService;
import pe.com.nttdata.account.model.document.Account;
import pe.com.nttdata.account.model.request.AccountRequest;
import pe.com.nttdata.account.model.service.AccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("${path.accounts}")
@AllArgsConstructor
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    private final EnterpriseCostumerService enterpriseCostumerService;

    @GetMapping
    public Flux<Account> getAccounts() { return accountService.getAll(); }

    @GetMapping("/{id}")
    public Mono<Account> getAccount(@PathVariable Long id) { return accountService.findById(id); }

    @PostMapping
    public Mono<Account> register(@Valid @RequestBody AccountRequest accountRequest) throws ExecutionException,
            InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.save(account);
    }

    @PostMapping("/saving-account")
    public Mono<Account> registerSavingAccount(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.saveSavingAccount(account);
    }

    @PostMapping("/current-account")
    public Mono<Account> registerCurrentAccount(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.saveCurrentAccount(account);
    }

    @PostMapping("/fixed-term")
    public Mono<Account> registerFixedTerm(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.saveFixedTerm(account);
    }

    @PostMapping("/personal-credit")
    public Mono<Account> registerPersonalCredit(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.savePersonalCredit(account);
    }

    @PostMapping("/business-credit")
    public Mono<Account> registerBusinessCredit(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.saveBusinessCredit(account);
    }

    @PostMapping("/credit-card")
    public Mono<Account> registerCreditCard(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.saveCreditCard(account);
    }

    @PutMapping
    public Mono<Account> update(@Valid @RequestBody AccountRequest accountRequest) {
        Account account = new Account(accountRequest);
        return accountService.update(account);
    }

    @DeleteMapping("/{id}")
    public Mono<Account> delete(@PathVariable Long id) { return accountService.delete(id); }

    @GetMapping("/client/customers")
    public Flux<EnterpriseCustomer> getCustomers() { return enterpriseCostumerService.findAll(); }

}
