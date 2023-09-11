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

public class UserFullName {
    @Column(length = 50,nullable = false)
    private String firstName;

    @Column(length = 50,nullable = false)
    private String lastName;
}
