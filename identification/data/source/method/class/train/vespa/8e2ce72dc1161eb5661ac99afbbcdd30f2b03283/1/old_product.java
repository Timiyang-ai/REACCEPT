public static HttpRequest createRequest(CurrentContainer container,
                                            URI uri, Method method, InputStream requestData,
                                            Map<String, String> properties) {

        final com.yahoo.jdisc.http.HttpRequest clientRequest = com.yahoo.jdisc.http.HttpRequest
                .newClientRequest(new Request(container, uri), uri, method);
        setProperties(clientRequest, properties);
        return new HttpRequest(clientRequest, requestData);
    }