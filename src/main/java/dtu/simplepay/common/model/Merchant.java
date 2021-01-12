package dtu.simplepay.common.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Merchant extends Client {
    @Builder
    Merchant(String id, String name, String surname, String CPR) {
        super(id, name, surname, CPR);
    }
}
