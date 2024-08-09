@Test
    public void testRelinquishMastership() {
        mockService.relinquishMastershipSync(anyObject());
        expectLastCall();
        replay(mockService);

        final WebTarget wt = target();
        final Response response = wt.path("mastership/" + deviceId1.toString() +
                "/relinquish").request().get();
        assertThat(response.getStatus(), is(HttpURLConnection.HTTP_CREATED));
        String location = response.getLocation().toString();
        assertThat(location, Matchers.startsWith(deviceId1.toString()));
    }