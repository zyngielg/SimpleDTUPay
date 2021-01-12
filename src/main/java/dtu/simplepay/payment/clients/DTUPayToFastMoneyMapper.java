package dtu.simplepay.payment.clients;

import dtu.simplepay.common.model.Client;
import dtu.ws.fastmoney.User;

public class DTUPayToFastMoneyMapper {
    public static User mapUserToPerson(Client client) {
        var user = new User();
        user.setFirstName(client.getName());
        user.setLastName(client.getSurname());
        user.setCprNumber(client.getCpr());
        return user;
    }
}
