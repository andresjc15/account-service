package pe.com.nttdata.account.client.customer.model;

import lombok.Data;

@Data
public class PersonalCustomer extends Customer {

    private String name;
    private String lastName;

}
