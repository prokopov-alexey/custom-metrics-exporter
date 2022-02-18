package ap.cme.dto;

import lombok.Data;

@Data
public class StatusResponse {
    public static final String STATUS_HEALTHY = "healthy";

    public StatusResponse(String status) {
        this.status = status;
    }
    
    private final String status;
}
