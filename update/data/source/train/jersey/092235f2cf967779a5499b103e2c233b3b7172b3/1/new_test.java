@Test
    public void testAsyncGetWithTimeout() throws Exception {
        final Future<Response> responseFuture = target(PATH).path("timeout").request().async().get();
        // Request is being processed asynchronously.
        final Response response = responseFuture.get();

        // get() waits for the response
        assertEquals(503, response.getStatus());
        assertEquals("Operation time out.", response.readEntity(String.class));
    }