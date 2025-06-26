package edu.java.scrapper.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.http-client.retry")
public class HttpClientRetryProperties {
    private int maxAttempts = 3;
    private long backoffDelay = 500;
    private String backoffType = "exponential";
    private List<Integer> retryCodes = List.of(500, 502, 503, 504);

    public int getMaxAttempts() { return maxAttempts; }
    public void setMaxAttempts(int maxAttempts) { this.maxAttempts = maxAttempts; }
    public long getBackoffDelay() { return backoffDelay; }
    public void setBackoffDelay(long backoffDelay) { this.backoffDelay = backoffDelay; }
    public String getBackoffType() { return backoffType; }
    public void setBackoffType(String backoffType) { this.backoffType = backoffType; }
    public List<Integer> getRetryCodes() { return retryCodes; }
    public void setRetryCodes(List<Integer> retryCodes) { this.retryCodes = retryCodes; }
} 