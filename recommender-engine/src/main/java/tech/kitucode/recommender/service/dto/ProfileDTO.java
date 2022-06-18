package tech.kitucode.recommender.service.dto;

import lombok.Data;
import tech.kitucode.recommender.domain.Product;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProfileDTO {
    private Long id;

    private Long globalUserId;

    private List<Product> recommendations;

    private LocalDateTime updatedOn;
}
