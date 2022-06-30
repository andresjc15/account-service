package pe.com.nttdata.account.model.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class AccountRequest {

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
