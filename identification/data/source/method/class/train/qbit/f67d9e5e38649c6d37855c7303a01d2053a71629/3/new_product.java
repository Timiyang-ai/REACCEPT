public List<String> getPeers() {



        final URI uri = createURI("/peers");

        final HttpRequestBuilder httpRequestBuilder = RequestUtils
                .getHttpRequestBuilder(null, null, RequestOptions.BLANK, "");

        final HTTP.Response httpResponse = HTTP.getResponse(uri + "?" + httpRequestBuilder.paramString());

        if (httpResponse.code() != 200) {
            die("Unable to get the peers", uri, httpResponse.code(), httpResponse.body());
        }

        return fromJsonArray(httpResponse.body(), String.class);
    }