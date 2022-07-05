package pe.com.nttdata.account.type.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.com.nttdata.account.type.model.document.TypeAccount;
import pe.com.nttdata.account.type.model.service.TypeAccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/types")
@AllArgsConstructor
public class TypeAccountController {

    private final TypeAccountService typeAccountService;

    @GetMapping
    public Flux<TypeAccount> getTypes() { return typeAccountService.getAll(); }

    @GetMapping("/{id}")
    public Mono<TypeAccount> getType(@PathVariable Long id) { return typeAccountService.findById(id); }

    @PostMapping
    public Mono<TypeAccount> register(@RequestBody TypeAccount typeAccount)
            throws ExecutionException, InterruptedException {
        return typeAccountService.save(typeAccount);
    }

    @PutMapping
    public Mono<TypeAccount> update(@RequestBody TypeAccount typeAccount) {
        return typeAccountService.update(typeAccount);
    }

}
