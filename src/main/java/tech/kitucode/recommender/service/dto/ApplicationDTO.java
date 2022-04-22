package tech.kitucode.recommender.service.dto;

import lombok.Data;
import tech.kitucode.recommender.domain.enumerations.ApplicationStatus;
import tech.kitucode.recommender.domain.enumerations.ProductSource;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
public class ApplicationDTO {

    private Long id;

    private String name;

    private String developerEmail;

    private String developerPhoneNumber;

    private List<String> keyMetrics;

    private ProductSource productSource;

    private ApplicationStatus status;

    private String apiKey;

    private LocalDate createdOn;

    private LocalDate updatedOn;
}
