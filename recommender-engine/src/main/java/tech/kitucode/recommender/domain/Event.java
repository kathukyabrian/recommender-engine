package tech.kitucode.recommender.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table
@Entity(name = "tbl_events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long applicationId;

    private String eventType;

    private String eventValue;

    private LocalDateTime createdOn;

    private Long userId;

    private Long globalUserId;

    private Boolean isProcessed;

}
