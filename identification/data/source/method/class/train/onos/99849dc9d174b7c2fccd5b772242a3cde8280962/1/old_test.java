@Test
    public void testGetFlowByAppId() {
        setupMockFlowRules();

        expect(mockApplicationService.getId(anyObject())).andReturn(APP_ID).anyTimes();
        replay(mockApplicationService);

        expect(mockFlowService.getFlowRulesById(APP_ID)).andReturn(flowRules).anyTimes();
        replay(mockFlowService);

        final WebTarget wt = target();
        final String response = wt.path("flows/application/1").request().get(String.class);
        final JsonObject result = Json.parse(response).asObject();
        assertThat(result, notNullValue());

        assertThat(result.names(), hasSize(1));
        assertThat(result.names().get(0), is("flows"));
        final JsonArray jsonFlows = result.get("flows").asArray();
        assertThat(jsonFlows, notNullValue());
        assertThat(jsonFlows, hasFlowRule(flowRule1));
        assertThat(jsonFlows, hasFlowRule(flowRule2));
        assertThat(jsonFlows, hasFlowRule(flowRule3));
        assertThat(jsonFlows, hasFlowRule(flowRule4));
    }