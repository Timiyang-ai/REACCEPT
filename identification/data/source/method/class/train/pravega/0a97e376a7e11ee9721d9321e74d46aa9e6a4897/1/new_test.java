@Test
    public void testUpdateStream() throws ExecutionException, InterruptedException {
        String resourceURI = getURI() + "v1/scopes/" + scope1 + "/streams/" + stream1;

        // Test to update an existing stream
        when(mockControllerService.updateStream(any(), any(), any())).thenReturn(updateStreamStatus);
        Response response = addAuthHeaders(client.target(resourceURI).request()).buildPut(Entity.json(updateStreamRequest)).invoke();
        assertEquals("Update Stream Status", 200, response.getStatus());
        StreamProperty streamResponseActual = response.readEntity(StreamProperty.class);
        testExpectedVsActualObject(streamResponseExpected, streamResponseActual);
        response.close();

        // Test sending extra fields in the request object to check if json parser can handle it.
        response = addAuthHeaders(client.target(resourceURI).request()).buildPut(Entity.json(createStreamRequest)).invoke();
        assertEquals("Update Stream Status", 200, response.getStatus());
        streamResponseActual = response.readEntity(StreamProperty.class);
        testExpectedVsActualObject(streamResponseExpected, streamResponseActual);
        response.close();

        // Test to update an non-existing stream
        when(mockControllerService.updateStream(any(), any(), any())).thenReturn(updateStreamStatus2);
        response = addAuthHeaders(client.target(resourceURI).request()).buildPut(Entity.json(updateStreamRequest2)).invoke();
        assertEquals("Update Stream Status", 404, response.getStatus());
        response.close();

        // Test for validation of request object
        when(mockControllerService.updateStream(any(), any(), any())).thenReturn(updateStreamStatus3);
        response = addAuthHeaders(client.target(resourceURI).request()).buildPut(Entity.json(updateStreamRequest3)).invoke();
        // TODO: Server should be returning 400 here, change this once issue
        // https://github.com/pravega/pravega/issues/531 is fixed.
        assertEquals("Update Stream Status", 500, response.getStatus());
        response.close();

        // Test to update stream for non-existent scope
        when(mockControllerService.updateStream(any(), any(), any())).thenReturn(updateStreamStatus4);
        response = addAuthHeaders(client.target(resourceURI).request()).buildPut(Entity.json(updateStreamRequest)).invoke();
        assertEquals("Update Stream Status", 404, response.getStatus());
        response.close();
    }