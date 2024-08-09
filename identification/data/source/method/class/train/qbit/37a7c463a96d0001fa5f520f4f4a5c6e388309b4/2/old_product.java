public ConsulResponse<Map<String, List<String>>> getServices(
                                    final String datacenter, final String tag,
                                    final RequestOptions requestOptions) {



        final String path = rootPath + "/services";


        final HttpRequestBuilder httpRequestBuilder = RequestUtils.getHttpRequestBuilder(datacenter, tag, requestOptions, path);


        final HttpResponse httpResponse = httpClient.sendRequestAndWait(httpRequestBuilder.build());
        if (httpResponse.code()!=200) {
            die("Unable to retrieve the datacenters", path, httpResponse.code(), httpResponse.body());
        }

        //noinspection unchecked
        return (ConsulResponse<Map<String, List<String>>>)(Object) RequestUtils.consulResponse(Map.class, httpResponse);

    }