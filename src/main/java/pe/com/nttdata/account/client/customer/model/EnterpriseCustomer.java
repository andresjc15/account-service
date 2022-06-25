package pe.com.nttdata.account.client.customer.model;

import lombok.Data;

@Data
public class EnterpriseCustomer extends Customer {

    private String businessName;
    private Long RUC;

}
