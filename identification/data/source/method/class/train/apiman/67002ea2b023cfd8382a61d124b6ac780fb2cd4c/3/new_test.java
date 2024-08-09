protected void publishApi(FrameworkMethod method) {
        version++;

        try {
            // Get the policy class under test.
            TestingPolicy tp = method.getMethod().getAnnotation(TestingPolicy.class);
            if (tp == null) {
                tp = getTestClass().getJavaClass().getAnnotation(TestingPolicy.class);
            }
            if (tp == null) {
                throw new Exception("Missing test annotation @TestingPolicy.");
            }
            Class<? extends IPolicy> policyUnderTest = tp.value();

            // Get the configuration JSON to use
            Configuration config = method.getMethod().getAnnotation(Configuration.class);
            if (config == null) {
                config = getTestClass().getJavaClass().getAnnotation(Configuration.class);
            }
            if (config == null) {
                throw new Exception("Missing test annotation @Configuration.");
            }

            // Get the back end API simulator to use
            BackEndApi backEnd = method.getMethod().getAnnotation(BackEndApi.class);
            if (backEnd == null) {
                backEnd = getTestClass().getJavaClass().getAnnotation(BackEndApi.class);
            }
            Class<? extends IPolicyTestBackEndApi> backEndApi = null;
            if (backEnd == null) {
                backEndApi = EchoBackEndApi.class;
            } else {
                backEndApi = backEnd.value();
            }


            final Set<Throwable> errorHolder = new HashSet<>();

            Policy policy = new Policy();
            policy.setPolicyImpl("class:" + policyUnderTest.getName());
            policy.setPolicyJsonConfig(config.value());

            Api api = new Api();
            api.setEndpoint(backEndApi.getName());
            api.setEndpointType("TEST");
            api.setOrganizationId(orgId);
            api.setApiId(apiId);
            api.setVersion(String.valueOf(version));
            api.setPublicAPI(true);
            api.setApiPolicies(Collections.singletonList(policy));

            getEngine().getRegistry().publishApi(api, new IAsyncResultHandler<Void>() {
                @Override
                public void handle(IAsyncResult<Void> result) {
                    if (result.isError()) {
                        errorHolder.add(result.getError());
                    }
                }
            });

            if (!errorHolder.isEmpty()) {
                throw errorHolder.iterator().next();
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }