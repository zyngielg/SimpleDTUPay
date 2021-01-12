package dtu.simplepay.common.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
public abstract class Client implements Serializable {
    private String id;
    @NonNull private String name;
    @NonNull private String surname;
    @NonNull private String cpr;
}
