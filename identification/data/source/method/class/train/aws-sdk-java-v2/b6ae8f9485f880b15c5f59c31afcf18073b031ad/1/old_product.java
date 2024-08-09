public static SdkHttpFullRequest.Builder createSdkHttpRequest(OperationInfo operationInfo, URI endpoint) {
        SdkHttpFullRequest.Builder request = SdkHttpFullRequest
            .builder()
            .method(operationInfo.httpMethod())
            .uri(endpoint);

        return request.encodedPath(concatPaths(request.encodedPath(),
                                               addStaticQueryParametersToRequest(request, operationInfo.requestUri())));
    }