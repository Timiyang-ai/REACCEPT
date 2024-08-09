@Test
    public void testApply() {
        RateLimitingPolicy policy = new RateLimitingPolicy();
        String json = "{\r\n" +
                "  \"limit\" : 10,\r\n" +
                "  \"granularity\" : \"User\",\r\n" +
                "  \"period\" : \"Minute\",\r\n" +
                "  \"userHeader\" : \"X-Identity\"\r\n" +
                "}";
        Object config = policy.parseConfiguration(json);
        ServiceRequest request = new ServiceRequest();
        request.setContract(createTestContract());
        request.setType("GET");
        request.setApiKey("12345");
        request.setRemoteAddr("1.2.3.4");
        request.setDestination("/");
        request.getHeaders().put("X-Identity", "sclause"); //$NON-NLS-2$
        IPolicyContext context = Mockito.mock(IPolicyContext.class);
        final PolicyFailure failure = new PolicyFailure();
        Mockito.when(context.getComponent(IPolicyFailureFactoryComponent.class)).thenReturn(new IPolicyFailureFactoryComponent() {
            @Override
            public PolicyFailure createFailure(PolicyFailureType type, int failureCode, String message) {
                return failure;
            }
        });
        Mockito.when(context.getComponent(IRateLimiterComponent.class)).thenReturn(new InMemoryRateLimiterComponent());
        IPolicyChain<ServiceRequest> chain = null;

        for (int count = 0; count < 10; count++) {
            chain = Mockito.mock(IPolicyChain.class);
            policy.apply(request, context, config, chain);
            Mockito.verify(chain).doApply(request);
        }

        // Failure - only allow 10 per minute!
        chain = Mockito.mock(IPolicyChain.class);
        policy.apply(request, context, config, chain);
        Mockito.verify(chain).doFailure(failure);
    }