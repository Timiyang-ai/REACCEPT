@Test
    public void testRequestRoleFor() {
        expect(mockService.requestRoleForSync(anyObject())).andReturn(role1).anyTimes();
        replay(mockService);

        expect(mockDeviceService.getDevice(deviceId1)).andReturn(device1);
        replay(mockDeviceService);

        final WebTarget wt = target();
        final String response = wt.path("mastership/" + deviceId1.toString() +
                "/request").request().get(String.class);
        final JsonObject result = Json.parse(response).asObject();
        assertThat(result, notNullValue());

        assertThat(result.names(), hasSize(1));
        assertThat(result.names().get(0), is("role"));

        final String role = result.get("role").asString();
        assertThat(role, notNullValue());
        assertThat(role, is("MASTER"));
    }