    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:something")
                        .id("routeId")
                        .to("mock:result");
            }
        };
    }