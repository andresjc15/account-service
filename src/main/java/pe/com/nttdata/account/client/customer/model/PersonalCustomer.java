package pe.com.nttdata.account.client.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalCustomer extends Customer {

    private String name;
    private String lastName;

}
