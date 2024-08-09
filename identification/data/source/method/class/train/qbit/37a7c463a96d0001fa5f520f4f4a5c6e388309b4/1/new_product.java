public ConsulResponse<Map<String, List<String>>> getServices(
            @SuppressWarnings("SameParameterValue") final String datacenter, @SuppressWarnings("SameParameterValue") final String tag,
            final RequestOptions requestOptions) {




        final URI uri = createURI("/services");



        final HttpRequestBuilder httpRequestBuilder = RequestUtils.getHttpRequestBuilder(datacenter, tag, requestOptions, "/");



        HTTP.Response httpResponse = HTTP.getResponse(uri.toString() + "?" +  httpRequestBuilder.paramString());

        if (httpResponse.code() != 200) {
            die("Unable to retrieve the datacenters", uri, httpResponse.code(), httpResponse.body());
        }

        //noinspection unchecked
        return (ConsulResponse<Map<String, List<String>>>) (Object) RequestUtils.consulResponse(Map.class, httpResponse);

    }