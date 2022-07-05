package pe.com.nttdata.account.model.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.com.nttdata.account.model.request.AccountRequest;
import pe.com.nttdata.account.type.model.document.TypeAccount;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "accounts")
public class Account {

    @Transient
    public static final String SEQUENCE_NAME = "accounts_sequence";

    @Id
    private long id;

    private String hexId;

    private String customerId;
    private TypeAccount typeAccount;

    private Long numberAccount;

    private BigDecimal amount;
    private List<Map<String, Object>> transactions;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

    public Account(AccountRequest accountRequest) {
        this.id = accountRequest.getId();
        this.hexId = accountRequest.getHexId();
        this.customerId = accountRequest.getCustomerId();
        this.typeAccount = accountRequest.getTypeAccount();
        this.numberAccount = accountRequest.getNumberAccount();
        this.amount = accountRequest.getAmount();
        this.transactions = accountRequest.getTransactions();
        this.isActive = accountRequest.isActive();
        this.createdAt = accountRequest.getCreatedAt();
        this.updatedAt = accountRequest.getUpdatedAt();
    }

}
