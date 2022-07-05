package pe.com.nttdata.account.type.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "types_account")
public class TypeAccount {

    @Transient
    public static final String SEQUENCE_NAME = "type_accounts_sequence";

    @Id
    private Long id;

    private String name;
    private String sub;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

}
