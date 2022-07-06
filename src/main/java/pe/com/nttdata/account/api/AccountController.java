package pe.com.nttdata.account.api;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get all accounts")
    public Flux<Account> getAccounts() { return accountService.getAll(); }

    @GetMapping("/{id}")
    @Operation(summary = "Get account by id")
    public Mono<Account> getAccount(@PathVariable Long id) { return accountService.findById(id); }

    @PostMapping
    @Operation(summary = "Register account")
    public Mono<Account> register(@Valid @RequestBody AccountRequest accountRequest) throws ExecutionException,
            InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.save(account);
    }

    @PostMapping("/saving-account")
    @Operation(summary = "Register Saving account")
    public Mono<Account> registerSavingAccount(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.saveSavingAccount(account);
    }

    @PostMapping("/current-account")
    @Operation(summary = "Register Current account")
    public Mono<Account> registerCurrentAccount(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.saveCurrentAccount(account);
    }

    @PostMapping("/fixed-term")
    @Operation(summary = "Register Fixed term account")
    public Mono<Account> registerFixedTerm(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.saveFixedTerm(account);
    }

    @PostMapping("/personal-credit")
    @Operation(summary = "Register Personal credit")
    public Mono<Account> registerPersonalCredit(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.savePersonalCredit(account);
    }

    @PostMapping("/business-credit")
    @Operation(summary = "Register Bussiness credit")
    public Mono<Account> registerBusinessCredit(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.saveBusinessCredit(account);
    }

    @PostMapping("/credit-card")
    @Operation(summary = "Register Credit card")
    public Mono<Account> registerCreditCard(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.saveCreditCard(account);
    }

    @PutMapping
    @Operation(summary = "Register Update account")
    public Mono<Account> update(@Valid @RequestBody AccountRequest accountRequest) {
        Account account = new Account(accountRequest);
        return accountService.update(account);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Register delete account")
    public Mono<Account> delete(@PathVariable Long id) { return accountService.delete(id); }

    @GetMapping("/client/customers")
    public Flux<EnterpriseCustomer> getCustomers() { return enterpriseCostumerService.findAll(); }

}
