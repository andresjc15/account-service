package pe.com.nttdata.account.client.customer.model.service;

import pe.com.nttdata.account.client.customer.model.EnterpriseCustomer;
import reactor.core.publisher.Flux;

public interface EnterpriseCostumerService {

    public Flux<EnterpriseCustomer> findAll();

}
