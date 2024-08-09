@Test
    public void testUpdateStream() throws ExecutionException, InterruptedException {
        // Test to update an existing stream
        when(mockControllerService.alterStream(any())).thenReturn(updateStreamStatus);
        response = target(resourceURI).request().async().put(Entity.json(updateStreamRequest));
        streamResponseActual = response.get().readEntity(StreamProperty.class);
        assertEquals("Update Stream Status", 201, response.get().getStatus());
        testExpectedVsActualObject(streamResponseExpected, streamResponseActual);

        // Test to update an non-existing stream
        when(mockControllerService.alterStream(any())).thenReturn(updateStreamStatus2);
        response = target(resourceURI).request().async().put(Entity.json(updateStreamRequest2));
        assertEquals("Update Stream Status", 404, response.get().getStatus());

        // Test for validation of request object
        when(mockControllerService.alterStream(any())).thenReturn(updateStreamStatus3);
        response = target(resourceURI).request().async().put(Entity.json(updateStreamRequest3));
        // TODO: Server should be returning 400 here, change this once issue
        // https://github.com/pravega/pravega/issues/531 is fixed.
        assertEquals("Update Stream Status", 500, response.get().getStatus());

        // Test to update stream for non-existent scope
        when(mockControllerService.alterStream(any())).thenReturn(updateStreamStatus4);
        response = target(resourceURI).request().async().put(Entity.json(updateStreamRequest));
        assertEquals("Update Stream Status", 404, response.get().getStatus());
    }