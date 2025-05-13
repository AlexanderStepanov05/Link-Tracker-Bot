package edu.java.bot.dto;

public class LinkResponse {
    private String url;
    private String status;

    public LinkResponse(String url, String status) {
        this.url = url;
        this.status = status;
    }

    public String getUrl() { return url; }
    public String getStatus() { return status; }
}
