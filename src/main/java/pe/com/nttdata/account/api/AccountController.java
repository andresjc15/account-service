package pe.com.nttdata.account.api;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import pe.com.nttdata.account.client.customer.model.EnterpriseCustomer;
import pe.com.nttdata.account.client.customer.model.service.EnterpriseCostumerService;
import pe.com.nttdata.account.model.document.Account;
import pe.com.nttdata.account.model.request.AccountRequest;
import pe.com.nttdata.account.model.service.AccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("${path.accounts}")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final EnterpriseCostumerService enterpriseCostumerService;

    @CircuitBreaker(name = "account", fallbackMethod = "alternativeMethod")
    @TimeLimiter(name = "account")
    @GetMapping
    @Operation(summary = "Get all accounts")
    public Flux<Account> getAccounts() { return accountService.getAll(); }

    @CircuitBreaker(name = "account", fallbackMethod = "alternativeMethod")
    @TimeLimiter(name = "account")
    @GetMapping("/{id}")
    @Operation(summary = "Get account by id")
    public Mono<Account> getAccount(@PathVariable Long id) { return accountService.findById(id); }

    @CircuitBreaker(name = "account", fallbackMethod = "alternativeMethod")
    @TimeLimiter(name = "account")
    @PostMapping
    @Operation(summary = "Register account")
    public Mono<Account> register(@Valid @RequestBody AccountRequest accountRequest) throws ExecutionException,
            InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.save(account);
    }

    @CircuitBreaker(name = "account", fallbackMethod = "alternativeMethod")
    @TimeLimiter(name = "account")
    @PostMapping("/saving-account")
    @Operation(summary = "Register Saving account")
    public Mono<Account> registerSavingAccount(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.saveSavingAccount(account);
    }

    @CircuitBreaker(name = "account", fallbackMethod = "alternativeMethod")
    @TimeLimiter(name = "account")
    @PostMapping("/current-account")
    @Operation(summary = "Register Current account")
    public Mono<Account> registerCurrentAccount(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.saveCurrentAccount(account);
    }

    @CircuitBreaker(name = "account", fallbackMethod = "alternativeMethod")
    @TimeLimiter(name = "account")
    @PostMapping("/fixed-term")
    @Operation(summary = "Register Fixed term account")
    public Mono<Account> registerFixedTerm(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.saveFixedTerm(account);
    }

    @CircuitBreaker(name = "account", fallbackMethod = "alternativeMethod")
    @TimeLimiter(name = "account")
    @PostMapping("/personal-credit")
    @Operation(summary = "Register Personal credit")
    public Mono<Account> registerPersonalCredit(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.savePersonalCredit(account);
    }

    @CircuitBreaker(name = "account", fallbackMethod = "alternativeMethod")
    @TimeLimiter(name = "account")
    @PostMapping("/business-credit")
    @Operation(summary = "Register Bussiness credit")
    public Mono<Account> registerBusinessCredit(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.saveBusinessCredit(account);
    }

    @CircuitBreaker(name = "account", fallbackMethod = "alternativeMethod")
    @TimeLimiter(name = "account")
    @PostMapping("/credit-card")
    @Operation(summary = "Register Credit card")
    public Mono<Account> registerCreditCard(@Valid @RequestBody AccountRequest accountRequest)
            throws ExecutionException, InterruptedException {
        Account account = new Account(accountRequest);
        return accountService.saveCreditCard(account);
    }

    @CircuitBreaker(name = "account", fallbackMethod = "alternativeMethod")
    @TimeLimiter(name = "account")
    @PutMapping
    @Operation(summary = "Register Update account")
    public Mono<Account> update(@Valid @RequestBody AccountRequest accountRequest) {
        Account account = new Account(accountRequest);
        return accountService.update(account);
    }

    @CircuitBreaker(name = "account", fallbackMethod = "alternativeMethod")
    @TimeLimiter(name = "account")
    @DeleteMapping("/{id}")
    @Operation(summary = "Register delete account")
    public Mono<Account> delete(@PathVariable Long id) { return accountService.delete(id); }

    @CircuitBreaker(name = "account", fallbackMethod = "alternativeMethod")
    @TimeLimiter(name = "account")
    @GetMapping("/client/customers")
    public Flux<EnterpriseCustomer> getCustomers() { return enterpriseCostumerService.findAll(); }

    @CircuitBreaker(name = "account", fallbackMethod = "alternativeMethod")
    @TimeLimiter(name = "account")
    @GetMapping("/error")
    public Flux<EnterpriseCustomer> getError() {
        throw new RuntimeException();
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleException(WebExchangeBindException e) {
        Map<String, Object> response = new HashMap<String, Object>();
        var errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        response.put("errors", errors);
        response.put("timestamp", new Date());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        return Mono.just(new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST));
    }

    public Mono<ResponseEntity<Map<String, Object>>> alternativeMethod(Long id, Integer quantity, Throwable e) {
        log.error("[Error]: " + e.getMessage());
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        return Mono.just(new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST));
    }

}
