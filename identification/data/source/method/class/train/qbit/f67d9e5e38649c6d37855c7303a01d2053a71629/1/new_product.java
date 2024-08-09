public String getLeader() {


        final URI uri = createURI("/leader");

        final HttpRequestBuilder httpRequestBuilder = RequestUtils
                .getHttpRequestBuilder(null, null, RequestOptions.BLANK, "");

        final HTTP.Response httpResponse = HTTP.getResponse(uri + "?" + httpRequestBuilder.paramString());

        if (httpResponse.code() != 200) {
            die("Unable to retrieve the leader", uri, httpResponse.code(), httpResponse.body());
        }

        return fromJson(httpResponse.body(), String.class).replace("\"", "").trim();
    }