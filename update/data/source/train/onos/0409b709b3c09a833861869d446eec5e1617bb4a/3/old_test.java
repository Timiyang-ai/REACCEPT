@Test
    public void testGetMasterFor() {
        expect(mockService.getMasterFor(anyObject())).andReturn(nodeId1).anyTimes();
        replay(mockService);

        final WebTarget wt = target();
        final String response = wt.path("mastership/" + deviceId1.toString() +
                                "/master").request().get(String.class);
        final JsonObject result = Json.parse(response).asObject();
        assertThat(result, notNullValue());

        assertThat(result.names(), hasSize(1));
        assertThat(result.names().get(0), is("node"));

        final String node = result.get("node").asString();
        assertThat(node, notNullValue());
        assertThat(node, is("node:1"));
    }