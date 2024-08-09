@Test
    public void testCreateStream() throws ExecutionException, InterruptedException {
        // Test to create a stream which doesn't exist
        when(mockControllerService.createStream(any(), anyLong())).thenReturn(createStreamStatus);
        response = target(streamResourceURI).request().async().post(Entity.json(createStreamRequest));
        assertEquals("Create Stream Status", 201, response.get().getStatus());
        streamResponseActual = response.get().readEntity(StreamProperty.class);
        testExpectedVsActualObject(streamResponseExpected, streamResponseActual);

        // Test to create a stream that already exists
        when(mockControllerService.createStream(any(), anyLong())).thenReturn(createStreamStatus2);
        response = target(streamResourceURI).request().async().post(Entity.json(createStreamRequest));
        assertEquals("Create Stream Status", 409, response.get().getStatus());

        // Test for validation of create stream request object
        when(mockControllerService.createStream(any(), anyLong())).thenReturn(createStreamStatus3);
        response = target(streamResourceURI).request().async().post(Entity.json(createStreamRequest2));
        // TODO: Server should be returning 400 here, change this once issue
        // https://github.com/pravega/pravega/issues/531 is fixed.
        assertEquals("Create Stream Status", 500, response.get().getStatus());
    }