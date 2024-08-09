@Test
    public void testMarshall_Client() throws Exception {
        Client client = new Client();
        client.setClientId("client-id");
        client.setOrganizationId("test-org");
        client.setVersion("1.0");

        Assert.assertEquals("{"
                + "\"organizationId\":\"test-org\","
                + "\"clientId\":\"client-id\","
                + "\"version\":\"1.0\","
                + "\"contracts\":[]"
            + "}", ESRegistryMarshalling.marshall(client).string());

        Contract contract = new Contract();
        contract.setApiKey("12345");
        contract.setPlan("Silver");
        contract.setApiId("api-id");
        contract.setApiOrgId("api-test-org");
        contract.setApiVersion("1.7");
        client.getContracts().add(contract);

        Assert.assertEquals("{"
                + "\"organizationId\":\"test-org\","
                + "\"clientId\":\"client-id\","
                + "\"version\":\"1.0\","
                + "\"contracts\":["
                    + "{"
                        + "\"apiKey\":\"12345\","
                        + "\"plan\":\"Silver\","
                        + "\"apiOrgId\":\"api-test-org\","
                        + "\"apiId\":\"api-id\","
                        + "\"apiVersion\":\"1.7\","
                        + "\"policies\":[]"
                    + "}"
                + "]"
            + "}", ESRegistryMarshalling.marshall(client).string());

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
                + "\"clientId\":\"client-id\","
                + "\"version\":\"1.0\","
                + "\"contracts\":["
                    + "{"
                        + "\"apiKey\":\"12345\","
                        + "\"plan\":\"Silver\","
                        + "\"apiOrgId\":\"api-test-org\","
                        + "\"apiId\":\"api-id\","
                        + "\"apiVersion\":\"1.7\","
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
            + "}", ESRegistryMarshalling.marshall(client).string());
    }