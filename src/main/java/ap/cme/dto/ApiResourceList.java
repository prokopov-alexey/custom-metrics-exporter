package ap.cme.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ApiResourceList {
    private final String kind = "APIResourceList";
    private final String apiVersion;
    private final String groupVersion;
    private final List<Resource> resources;

    public ApiResourceList(String apiVersion, String groupVersion) {
        this.apiVersion = apiVersion;
        this.groupVersion = groupVersion;
        this.resources = new ArrayList<>();
    }
}
