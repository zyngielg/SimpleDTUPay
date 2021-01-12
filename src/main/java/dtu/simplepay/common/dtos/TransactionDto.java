package dtu.simplepay.common.dtos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TransactionDto implements Serializable {
    @Setter @Getter private String customerId;
    @Setter @Getter private String merchantId;
    @Setter @Getter private BigDecimal amount;
}
