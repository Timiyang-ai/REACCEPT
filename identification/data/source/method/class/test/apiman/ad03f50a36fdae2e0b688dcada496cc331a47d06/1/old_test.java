@Test
    public void testApply() {
        IPWhitelistPolicy policy = new IPWhitelistPolicy();
        String json = "{" +  //$NON-NLS-1$
                "  \"ipList\" : [" +  //$NON-NLS-1$
                "    \"1.2.3.4\"," +  //$NON-NLS-1$
                "    \"3.4.5.6\"," +  //$NON-NLS-1$
                "    \"10.0.0.11\"" +  //$NON-NLS-1$
                "  ]" +  //$NON-NLS-1$
                "}"; //$NON-NLS-1$
        Object config = policy.parseConfiguration(json);
        ServiceRequest request = new ServiceRequest();
        request.setType("GET"); //$NON-NLS-1$
        request.setApiKey("12345"); //$NON-NLS-1$
        request.setRemoteAddr("1.2.3.4"); //$NON-NLS-1$
        request.setDestination("/"); //$NON-NLS-1$
        IPolicyContext context = Mockito.mock(IPolicyContext.class);
        IPolicyChain chain = Mockito.mock(IPolicyChain.class);
        
        // Success
        policy.apply(request, context, config, chain);
        Mockito.verify(chain).doApply(request);
        
        // Failure
        chain = Mockito.mock(IPolicyChain.class);
        request.setRemoteAddr("9.8.7.6"); //$NON-NLS-1$
        policy.apply(request, context, config, chain);
        ArgumentCaptor<PolicyFailure> argument = ArgumentCaptor.forClass(PolicyFailure.class);
        Mockito.verify(chain).doFailure(argument.capture());
    }