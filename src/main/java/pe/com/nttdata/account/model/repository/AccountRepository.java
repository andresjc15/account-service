package pe.com.nttdata.account.model.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.com.nttdata.account.model.document.Account;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, Long> {
}
