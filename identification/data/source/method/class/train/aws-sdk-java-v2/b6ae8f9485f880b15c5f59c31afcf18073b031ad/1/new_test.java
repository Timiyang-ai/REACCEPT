    @Test
    public void createSdkHttpRequest_SetsHttpMethodAndEndpointCorrectly() {
        SdkHttpFullRequest.Builder sdkHttpRequest = ProtocolUtils.createSdkHttpRequest(
            OperationInfo.builder()
                         .httpMethod(SdkHttpMethod.DELETE)
                         .build(), URI.create("http://localhost:8080"));
        assertThat(sdkHttpRequest.protocol()).isEqualTo("http");
        assertThat(sdkHttpRequest.host()).isEqualTo("localhost");
        assertThat(sdkHttpRequest.port()).isEqualTo(8080);
        assertThat(sdkHttpRequest.method()).isEqualTo(SdkHttpMethod.DELETE);
    }