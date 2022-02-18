package ap.cme.dto;

import lombok.Data;

@Data
public class Metadata {
    private String selfLink;

    public Metadata(String selfLink) {
        this.selfLink = selfLink;
    }
}
