package com.utkarsh2573.gatewayserver.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import org.springframework.http.HttpHeaders;
import java.util.List;

@Component
public class FilterUtility {
    public static final String CorrelationId = "DemoBank-CorrelationId";

    public String getCorrelationId(HttpHeaders requestHeaders) {
        if (requestHeaders.get(CorrelationId)!=null){
            List<String> requestHeaderList = requestHeaders.get(CorrelationId);
            return requestHeaderList.stream().findFirst().get();
        }
        return null;
    }

    public ServerWebExchange setRequestHeaders(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeaders(exchange, CorrelationId, correlationId);
    }
}
