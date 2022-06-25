package pe.com.nttdata.account.client.customer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.nttdata.account.client.customer.model.Customer;
import pe.com.nttdata.account.client.customer.model.EnterpriseCustomer;
import pe.com.nttdata.account.client.customer.model.service.EnterpriseCostumerService;
import reactor.core.publisher.Flux;

@Service
public class EnterpriseCostumerServiceImpl implements EnterpriseCostumerService {

    private static final Logger log = LoggerFactory.getLogger(EnterpriseCostumerServiceImpl.class);

    @Autowired
    private WebClient client;

    @Override
    public Flux<EnterpriseCustomer> findAll() {
        return client.get().accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> response.bodyToFlux(EnterpriseCustomer.class));
    }
}
