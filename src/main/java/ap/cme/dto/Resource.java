package ap.cme.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Resource {
    private final String kind;
    private final String name;
    private final String singularName;
    private final boolean nemaspaced;
    private final List<String> verbs;

    public Resource(String kind, String name, String singularName, boolean nemaspaced) {
        this.kind = kind;
        this.name = name;
        this.singularName = singularName;
        this.nemaspaced = nemaspaced;
        this.verbs = new ArrayList<>();
    }
}
