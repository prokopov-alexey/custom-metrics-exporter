package ap.cme.dto;

import lombok.Data;

@Data
public class MetricsObject {
    private String metricName;
    private String timestamp;    
    private String value;
    private DescribedObject describedObject;

    public MetricsObject(String metricName, String timestamp, String value, DescribedObject describedObject) {
        this.metricName = metricName;
        this.timestamp = timestamp;
        this.value = value;
        this.describedObject = describedObject;
    }
}
