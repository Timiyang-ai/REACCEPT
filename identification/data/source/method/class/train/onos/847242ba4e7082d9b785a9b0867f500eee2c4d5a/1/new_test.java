@Test
    public void getAppIdByName() {
        expect(coreService.getAppId("app2")).andReturn(id2);
        replay(coreService);

        WebTarget wt = target();
        String response = wt.path("applications/ids/entry")
                .queryParam("name", "app2").request().get(String.class);

        JsonObject result = Json.parse(response).asObject();
        assertThat(result, notNullValue());

        assertThat(result, matchesAppId(id2));

        verify(coreService);
    }