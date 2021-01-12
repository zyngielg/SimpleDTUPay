package dtu.simplepay.common.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Transaction implements Serializable {
    @Setter @Getter Customer customer;
    @Setter @Getter Merchant merchant;
    @Setter @Getter
    BigDecimal amount;
}
