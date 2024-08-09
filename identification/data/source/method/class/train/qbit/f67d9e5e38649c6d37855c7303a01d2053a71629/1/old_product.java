public String getLeader() {


        final String path = rootPath + "/leader";

        final HttpRequestBuilder httpRequestBuilder = RequestUtils
                .getHttpRequestBuilder(null, null, RequestOptions.BLANK, path);

        final HttpResponse httpResponse = httpClient.sendRequestAndWait(httpRequestBuilder.build());
        if (httpResponse.code() != 200) {
            die("Unable to retrieve the leader", path, httpResponse.code(), httpResponse.body());
        }

        return fromJson(httpResponse.body(), String.class).replace("\"", "").trim();
    }