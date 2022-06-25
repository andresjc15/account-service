package pe.com.nttdata.account.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Document(collection = "accounts")
public class Account {

    @Transient
    public static final String SEQUENCE_NAME = "accounts_sequence";

    @Id
    private long id;

    private String hexId;

    private String customerId;

    private Long numberAccount;

    private BigDecimal amount;
    private List<Map<String, Object>> transactions;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

}
