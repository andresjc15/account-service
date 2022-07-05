package pe.com.nttdata.account.type.model.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.com.nttdata.account.type.model.document.TypeAccount;

@Repository
public interface TypeAccountRepository extends ReactiveMongoRepository<TypeAccount, Long> {
}
