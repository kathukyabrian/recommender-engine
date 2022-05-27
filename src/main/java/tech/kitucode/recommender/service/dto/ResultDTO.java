package tech.kitucode.recommender.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResultDTO {
    private Long eventId;

    private List<Integer> recommended;
}
