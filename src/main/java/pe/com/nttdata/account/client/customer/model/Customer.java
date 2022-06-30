package pe.com.nttdata.account.client.customer.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class Customer {

    private Long id;

    private String address;

    //@NotBlank(message = "DNI no puede ser nulo o vacio")
    private Integer dni;
    private Integer phone;

    @NotBlank(message = "Email no puede ser nulo o vacio")
    private String email;

    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

}
