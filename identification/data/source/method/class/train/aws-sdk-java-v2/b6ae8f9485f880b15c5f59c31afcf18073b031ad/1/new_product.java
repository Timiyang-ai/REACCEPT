public static SdkHttpFullRequest.Builder createSdkHttpRequest(OperationInfo operationInfo, URI endpoint) {
        SdkHttpFullRequest.Builder request = SdkHttpFullRequest
            .builder()
            .method(operationInfo.httpMethod())
            .uri(endpoint);

        return request.encodedPath(SdkHttpUtils.appendUri(request.encodedPath(),
                                                addStaticQueryParametersToRequest(request, operationInfo.requestUri())));
    }