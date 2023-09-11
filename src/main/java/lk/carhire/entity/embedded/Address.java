package lk.carhire.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Address {

    @Column(length = 100,nullable = false)
    private String street;

    @Column( length = 100,nullable = false)
    private String city;

    @Column(length = 100,nullable = false)
    private String district;
}
