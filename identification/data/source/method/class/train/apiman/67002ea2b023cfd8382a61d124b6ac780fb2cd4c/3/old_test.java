protected void publishService(FrameworkMethod method) {
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

            // Get the back end service simulator to use
            BackEndService backEnd = method.getMethod().getAnnotation(BackEndService.class);
            if (backEnd == null) {
                backEnd = getTestClass().getJavaClass().getAnnotation(BackEndService.class);
            }
            Class<? extends IPolicyTestBackEndService> backEndService = null;
            if (backEnd == null) {
                backEndService = EchoBackEndService.class;
            } else {
                backEndService = backEnd.value();
            }


            final Set<Throwable> errorHolder = new HashSet<>();

            Policy policy = new Policy();
            policy.setPolicyImpl("class:" + policyUnderTest.getName());
            policy.setPolicyJsonConfig(config.value());

            Service service = new Service();
            service.setEndpoint(backEndService.getName());
            service.setEndpointType("TEST");
            service.setOrganizationId(orgId);
            service.setServiceId(serviceId);
            service.setVersion(String.valueOf(version));
            service.setPublicService(true);
            service.setServicePolicies(Collections.singletonList(policy));

            getEngine().getRegistry().publishService(service, new IAsyncResultHandler<Void>() {
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