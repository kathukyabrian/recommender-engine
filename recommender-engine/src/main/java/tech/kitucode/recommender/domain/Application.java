package tech.kitucode.recommender.domain;

import lombok.Data;
import tech.kitucode.recommender.domain.enumerations.ApplicationStatus;
import tech.kitucode.recommender.domain.enumerations.ProductSource;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tbl_applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String developerEmail;

    private String developerPhoneNumber;

    private String keyMetrics;

    @Enumerated(EnumType.STRING)
    private ProductSource productSource;

    private ApplicationStatus status;

    private String apiKey;

    private LocalDate createdOn;

    private LocalDate updatedOn;
}
