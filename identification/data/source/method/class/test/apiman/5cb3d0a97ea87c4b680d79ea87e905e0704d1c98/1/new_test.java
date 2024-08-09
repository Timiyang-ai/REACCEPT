@Test
    public void testApply() {
        IPWhitelistPolicy policy = new IPWhitelistPolicy();
        String json = "{" + 
                "  \"ipList\" : [" + 
                "    \"1.2.3.4\"," + 
                "    \"3.4.5.6\"," + 
                "    \"10.0.0.11\"" + 
                "  ]" + 
                "}";
        Object config = policy.parseConfiguration(json);
        policy.setConfiguration(config);
        ServiceRequest request = new ServiceRequest();
        request.setType("GET");
        request.setApiKey("12345");
        request.setRemoteAddr("1.2.3.4");
        request.setDestination("/");
        IPolicyContext context = Mockito.mock(IPolicyContext.class);
        IPolicyChain<ServiceRequest> chain = Mockito.mock(IPolicyChain.class);
        
        // Success
        policy.apply(request, context, chain);
        Mockito.verify(chain).doApply(request);
        
        // Failure
        final PolicyFailure failure = new PolicyFailure();
        Mockito.when(context.getComponent(IPolicyFailureFactoryComponent.class)).thenReturn(new IPolicyFailureFactoryComponent() {
            @Override
            public PolicyFailure createFailure(PolicyFailureType type, int failureCode, String message) {
                return failure;
            }
        });
        chain = Mockito.mock(IPolicyChain.class);
        request.setRemoteAddr("9.8.7.6");
        policy.apply(request, context, chain);
        Mockito.verify(chain).doFailure(failure);
    }