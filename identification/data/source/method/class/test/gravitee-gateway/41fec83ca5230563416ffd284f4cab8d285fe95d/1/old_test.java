    @Test
    public void add_simpleApi() {
        Api api = new ApiDefinitionBuilder().name("api-test")
                .proxy(new ProxyDefinitionBuilder().contextPath("/team").target("default", "http://localhost/target").build()).build();
        api.setPlans(Collections.singletonList(new Plan()));
        apiManager.deploy(api);

        verify(eventManager, only()).publishEvent(ReactorEvent.DEPLOY, api);
    }