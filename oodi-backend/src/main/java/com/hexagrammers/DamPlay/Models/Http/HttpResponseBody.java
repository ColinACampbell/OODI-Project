package com.hexagrammers.DamPlay.Models.Http;

public class HttpResponseBody {
    private Object data;
    private String token;

    public HttpResponseBody()
    {

    }

    public HttpResponseBody(Object data)
    {
        this.data = data;
    }

    public HttpResponseBody(String token )
    {
        this.token = token;
    }

    public Object getData() {
        return data;
    }

    public String getToken() {
        return token;
    }
}
