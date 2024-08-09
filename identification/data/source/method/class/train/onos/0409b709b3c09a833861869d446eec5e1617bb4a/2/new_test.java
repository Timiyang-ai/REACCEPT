@Test
    public void testSetRole() {
        mockAdminService.setRoleSync(anyObject(), anyObject(), anyObject());
        expectLastCall();
        replay(mockAdminService);

        final WebTarget wt = target();
        final InputStream jsonStream = MetersResourceTest.class
                .getResourceAsStream("put-set-roles.json");
        final Response response = wt.path("mastership")
                                    .request().put(Entity.json(jsonStream));
        assertThat(response.getStatus(), is(HttpURLConnection.HTTP_OK));
    }