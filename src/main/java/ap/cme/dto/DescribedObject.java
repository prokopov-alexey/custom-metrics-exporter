package ap.cme.dto;

import lombok.Data;

@Data
public class DescribedObject {
    private String kind;
    private String namespace;
    private String name;
    private String apiVersion;

    public DescribedObject(String kind, String namespace, String name, String apiVersion) {
        this.kind = kind;
        this.namespace = namespace;
        this.name = name;
        this.apiVersion = apiVersion;
    }
}
