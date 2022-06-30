package pe.com.nttdata.account.client.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.nttdata.account.client.customer.model.EnterpriseCustomer;
import pe.com.nttdata.account.client.customer.model.service.EnterpriseCostumerService;
import reactor.core.publisher.Flux;

@Service
public class EnterpriseCostumerServiceImpl implements EnterpriseCostumerService {

    @Autowired
    private WebClient client;

    @Override
    public Flux<EnterpriseCustomer> findAll() {
        return client.get().accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> response.bodyToFlux(EnterpriseCustomer.class));
    }

}
