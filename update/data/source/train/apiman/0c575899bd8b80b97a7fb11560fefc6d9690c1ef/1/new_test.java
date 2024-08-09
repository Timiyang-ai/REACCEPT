@Test
    public void testMarshall_ServiceContract() throws Exception {
        ServiceContract sc = new ServiceContract();
        sc.setApikey("12345");
        sc.setPlan("Gold");
        sc.setPolicies(new ArrayList<Policy>());

        Service service = new Service();
        service.setServicePolicies(null);
        service.setEndpoint("http://host/path/to/svc");
        service.setEndpointType("REST");
        service.setEndpointContentType("json");
        service.setOrganizationId("test-org");
        service.setPublicService(true);
        service.setServiceId("service-id");
        service.setVersion("1.0");
        sc.setService(service);

        Application app = new Application();
        app.setApplicationId("app-id");
        app.setOrganizationId("test-org");
        app.setVersion("1.0");
        sc.setApplication(app);

        Policy policy = new Policy();
        policy.setPolicyImpl("policy-1-impl");
        policy.setPolicyJsonConfig("POLICY-1-JSON-CONFIG");
        sc.getPolicies().add(policy);

        Policy policy2 = new Policy();
        policy2.setPolicyImpl("policy-2-impl");
        policy2.setPolicyJsonConfig("POLICY-2-JSON-CONFIG");
        sc.getPolicies().add(policy2);

        Assert.assertEquals("{"
                + "\"apiKey\":\"12345\","
                + "\"plan\":\"Gold\","
                + "\"application\":{"
                    + "\"organizationId\":\"test-org\","
                    + "\"applicationId\":\"app-id\","
                    + "\"version\":\"1.0\","
                    + "\"contracts\":[]"
                + "},"
                + "\"service\":{"
                    + "\"endpoint\":\"http://host/path/to/svc\","
                    + "\"endpointType\":\"REST\","
                    + "\"endpointContentType\":\"json\","
                    + "\"publicService\":true,"
                    + "\"organizationId\":\"test-org\","
                    + "\"serviceId\":\"service-id\","
                    + "\"version\":\"1.0\","
                    + "\"endpointProperties\":[]"
                + "},"
                + "\"policies\":["
                    + "{"
                        + "\"policyImpl\":\"policy-1-impl\","
                        + "\"policyJsonConfig\":\"POLICY-1-JSON-CONFIG\""
                    + "},"
                    + "{"
                        + "\"policyImpl\":\"policy-2-impl\","
                        + "\"policyJsonConfig\":\"POLICY-2-JSON-CONFIG\""
                    + "}"
                + "]"
            + "}", ESRegistryMarshalling.marshall(sc).string());
    }