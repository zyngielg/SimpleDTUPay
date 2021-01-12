package dtu.simplepay;

import dtu.simplepay.common.model.Customer;
import dtu.simplepay.common.model.Merchant;
import dtu.simplepay.common.dtos.TransactionDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.List;

public class SimpleDTUPay {

    private static final String transactionsURI = "http://localhost:8080/transactions";
    private static final String customerURI = "http://localhost:8080/customers";
    private static final String merchantURI = "http://localhost:8080/merchants";

    private final Client client;

    public SimpleDTUPay() {
        this.client = ClientBuilder.newClient();
    }

    public boolean pay(BigDecimal amount, String cid, String mid) throws Exception {
        var transactionDto = new TransactionDto(cid, mid, amount);

        var response = client
                .target(transactionsURI)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(transactionDto, MediaType.APPLICATION_JSON));


        if (response.getStatus() == 404) {
            throw new Exception(response.readEntity(String.class));
        }
        return response.getStatus() == 200;
    }

    public List<TransactionDto> getTransactionList() {
        return client
                .target(transactionsURI)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {
                });
    }

    public String registerCustomer(Customer customer) {
        return client
                .target(customerURI)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(customer, MediaType.APPLICATION_JSON), String.class);
    }

    public String registerMerchant(Merchant merchant) {
        return client
                .target(merchantURI)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(merchant, MediaType.APPLICATION_JSON), String.class);
    }
}
