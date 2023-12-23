package org.example.requestDto;

import java.util.*;

public class RequestDto {
    private String endpoint;
    private Map<String, String> headers;
    private String body;

    public RequestDto() {
        this.endpoint = null;

        this.headers = new HashMap<>();
        headers.put("Content-Type", null);
        headers.put("User-Agent", null);
        headers.put("Accept", null);
        headers.put("Cookie", null);
        headers.put("Host", null);

        this.body = null;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeader(String key, String value) {
        headers.replace(key, value);
    }
    public void addHeader(String key, String value) {
        headers.putIfAbsent(key, value);
    }

    public String getHeader(String key){
        return headers.get(key);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestDto)) return false;
        RequestDto that = (RequestDto) o;
        return Objects.equals(getEndpoint(), that.getEndpoint()) && Objects.equals(getHeaders(), that.getHeaders()) && Objects.equals(getBody(), that.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEndpoint(), getHeaders(), getBody());
    }

    @Override
    public String toString() {
        return "RequestDto{" +
                "endpoint='" + endpoint + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}
