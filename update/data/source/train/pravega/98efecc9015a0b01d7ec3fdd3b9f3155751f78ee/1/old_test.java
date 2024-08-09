@Test
    public void testCreateStream() throws ExecutionException, InterruptedException {
        // Test to create a stream which doesn't exist
        when(mockControllerService.createStream(any(), anyLong())).thenReturn(createStreamStatus);
        response = target(streamResourceURI).request().async().post(Entity.json(createStreamRequest));
        streamResponseActual = response.get().readEntity(StreamResponse.class);
        assertEquals("Create Stream Status", 201, response.get().getStatus());
        testExpectedVsActualObject(streamResponseExpected, streamResponseActual);

        // Test to create a stream that already exists
        when(mockControllerService.createStream(any(), anyLong())).thenReturn(createStreamStatus2);
        response = target(streamResourceURI).request().async().post(Entity.json(createStreamRequest));
        assertEquals("Create Stream Status", 409, response.get().getStatus());
    }