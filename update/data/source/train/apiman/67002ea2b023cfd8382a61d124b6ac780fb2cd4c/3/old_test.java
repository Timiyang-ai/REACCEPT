@Test
    public void testMarshall_Application() throws Exception {
        Application app = new Application();
        app.setApplicationId("app-id");
        app.setOrganizationId("test-org");
        app.setVersion("1.0");

        Assert.assertEquals("{"
                + "\"organizationId\":\"test-org\","
                + "\"applicationId\":\"app-id\","
                + "\"version\":\"1.0\","
                + "\"contracts\":[]"
            + "}", ESRegistryMarshalling.marshall(app).string());

        Contract contract = new Contract();
        contract.setApiKey("12345");
        contract.setPlan("Silver");
        contract.setServiceId("service-id");
        contract.setServiceOrgId("service-test-org");
        contract.setServiceVersion("1.7");
        app.getContracts().add(contract);

        Assert.assertEquals("{"
                + "\"organizationId\":\"test-org\","
                + "\"applicationId\":\"app-id\","
                + "\"version\":\"1.0\","
                + "\"contracts\":["
                    + "{"
                        + "\"apiKey\":\"12345\","
                        + "\"plan\":\"Silver\","
                        + "\"serviceOrgId\":\"service-test-org\","
                        + "\"serviceId\":\"service-id\","
                        + "\"serviceVersion\":\"1.7\","
                        + "\"policies\":[]"
                    + "}"
                + "]"
            + "}", ESRegistryMarshalling.marshall(app).string());

        Policy policy = new Policy();
        policy.setPolicyImpl("policy-1-impl");
        policy.setPolicyJsonConfig("POLICY-1-JSON-CONFIG");
        contract.getPolicies().add(policy);

        Policy policy2 = new Policy();
        policy2.setPolicyImpl("policy-2-impl");
        policy2.setPolicyJsonConfig("POLICY-2-JSON-CONFIG");
        contract.getPolicies().add(policy2);

        Assert.assertEquals("{"
                + "\"organizationId\":\"test-org\","
                + "\"applicationId\":\"app-id\","
                + "\"version\":\"1.0\","
                + "\"contracts\":["
                    + "{"
                        + "\"apiKey\":\"12345\","
                        + "\"plan\":\"Silver\","
                        + "\"serviceOrgId\":\"service-test-org\","
                        + "\"serviceId\":\"service-id\","
                        + "\"serviceVersion\":\"1.7\","
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
                    + "}"
                + "]"
            + "}", ESRegistryMarshalling.marshall(app).string());
    }