public List<String> getPeers() {

        final String path = rootPath + "/peers";

        final HttpRequestBuilder httpRequestBuilder = RequestUtils
                .getHttpRequestBuilder(null, null, RequestOptions.BLANK, path);

        final HttpResponse httpResponse = httpClient.sendRequestAndWait(httpRequestBuilder.build());
        if (httpResponse.code() != 200) {
            die("Unable to get the peers", path, httpResponse.code(), httpResponse.body());
        }

        return fromJsonArray(httpResponse.body(), String.class);
    }