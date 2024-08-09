@Test
    public void testGetFlowByAppId() {
        setupMockFlows();

        expect(mockApplicationService.getId(anyObject())).andReturn(APP_ID).anyTimes();
        replay(mockApplicationService);

        expect(mockFlowService.getFlowEntriesById(APP_ID)).andReturn(flowEntries).anyTimes();
        replay(mockFlowService);

        final WebTarget wt = target();
        final String response = wt.path("/flows/application/1").request().get(String.class);
        final JsonObject result = Json.parse(response).asObject();
        assertThat(result, notNullValue());

        assertThat(result.names(), hasSize(1));
        assertThat(result.names().get(0), is("flows"));
        final JsonArray jsonFlows = result.get("flows").asArray();
        assertThat(jsonFlows, notNullValue());
        assertThat(jsonFlows, hasFlowRule(flow1));
        assertThat(jsonFlows, hasFlowRule(flow2));
        assertThat(jsonFlows, hasFlowRule(flow3));
        assertThat(jsonFlows, hasFlowRule(flow4));
    }