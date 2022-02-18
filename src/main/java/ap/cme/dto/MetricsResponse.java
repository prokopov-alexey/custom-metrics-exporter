package ap.cme.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class MetricsResponse {
    private String kind;
    private String apiVersion;
    private Metadata metadata;
    private final List<MetricsObject> items = new ArrayList<>();

    public MetricsResponse(String kind, String apiVersion, Metadata metadata) {
        this.kind = kind;
        this.apiVersion = apiVersion;
        this.metadata = metadata;
    }
}
