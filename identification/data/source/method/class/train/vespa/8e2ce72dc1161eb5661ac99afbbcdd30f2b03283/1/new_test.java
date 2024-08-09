    private HttpRequest createRequest() {
        return new HttpRequest()
                .setHost("local")
                .setPort(20)
                .addHttpHeader("x-foo", "bar")
                .setPath("/bah")
                .setHttpOperation(HttpRequest.HttpOp.PUT)
                .addUrlOption("urk", "arg")
                .setTimeout(25);
    }