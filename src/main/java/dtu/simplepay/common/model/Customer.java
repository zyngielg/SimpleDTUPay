package dtu.simplepay.common.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Customer extends Client {
    @Builder
    Customer(String id, String name, String surname, String CPR) {
        super(id, name, surname, CPR);
    }
}
