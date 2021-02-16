package guru.springframework.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Recipe recipe;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;
    private BigDecimal amount;
    private String description;
}
