package tech.kitucode.recommender.web.vm;

import lombok.Data;
import tech.kitucode.recommender.domain.enumerations.APIStatus;

@Data
public class ErrorVM {
    private Integer code;

    private APIStatus status;

    private String statusReason;

    public ErrorVM() {
    }

    public ErrorVM(Integer code, String statusReason) {
        this.status = APIStatus.ERROR;
        this.code = code;
        this.statusReason = statusReason;
    }

    public ErrorVM(Integer code, APIStatus status, String statusReason) {
        this.code = code;
        this.status = status;
        this.statusReason = statusReason;
    }
}
