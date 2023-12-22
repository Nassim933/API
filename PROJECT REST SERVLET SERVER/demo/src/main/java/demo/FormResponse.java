package demo;

public class FormResponse {
    private String id;
    private String summary;
    private String description;

    public FormResponse(String id, String summary, String description) {
        this.id = id;
        this.summary = summary;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }
}
