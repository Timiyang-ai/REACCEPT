@Test
    public void testCreateStream() throws ExecutionException, InterruptedException {
        String streamResourceURI = getURI() + "v1/scopes/" + scope1 + "/streams";

        // Test to create a stream which doesn't exist
        when(mockControllerService.createStream(any(), anyLong())).thenReturn(createStreamStatus);
        Response response = addAuthHeaders(client.target(streamResourceURI).request()).buildPost(Entity.json(createStreamRequest)).invoke();
        assertEquals("Create Stream Status", 201, response.getStatus());
        StreamProperty streamResponseActual = response.readEntity(StreamProperty.class);
        testExpectedVsActualObject(streamResponseExpected, streamResponseActual);
        response.close();

        // Test to create a stream which doesn't exist and has no Retention Policy set.
        when(mockControllerService.createStream(any(), anyLong())).thenReturn(createStreamStatus);
        response = addAuthHeaders(client.target(streamResourceURI).request()).buildPost(Entity.json(createStreamRequest4)).invoke();
        assertEquals("Create Stream Status", 201, response.getStatus());
        streamResponseActual = response.readEntity(StreamProperty.class);
        testExpectedVsActualObject(streamResponseExpected2, streamResponseActual);
        response.close();

        // Test to create a stream with internal stream name
        final CreateStreamRequest streamRequest = new CreateStreamRequest();
        streamRequest.setStreamName(NameUtils.getInternalNameForStream("stream"));
        when(mockControllerService.createStream(any(), anyLong())).thenReturn(createStreamStatus2);
        response = addAuthHeaders(client.target(streamResourceURI).request()).buildPost(Entity.json(streamRequest)).invoke();
        assertEquals("Create Stream Status", 400, response.getStatus());
        response.close();

        // Test to create a stream which doesn't exist and have Scaling Policy FIXED_NUM_SEGMENTS
        when(mockControllerService.createStream(any(), anyLong())).thenReturn(createStreamStatus);
        response = addAuthHeaders(client.target(streamResourceURI).request()).buildPost(Entity.json(createStreamRequest5)).invoke();
        assertEquals("Create Stream Status", 201, response.getStatus());
        streamResponseActual = response.readEntity(StreamProperty.class);
        testExpectedVsActualObject(streamResponseExpected3, streamResponseActual);
        response.close();

        // Test to create a stream that already exists
        when(mockControllerService.createStream(any(), anyLong())).thenReturn(createStreamStatus2);
        response = addAuthHeaders(client.target(streamResourceURI).request()).buildPost(Entity.json(createStreamRequest)).invoke();
        assertEquals("Create Stream Status", 409, response.getStatus());
        response.close();

        // Test for validation of create stream request object
        when(mockControllerService.createStream(any(), anyLong())).thenReturn(createStreamStatus3);
        response = addAuthHeaders(client.target(streamResourceURI).request()).buildPost(Entity.json(createStreamRequest2)).invoke();
        // TODO: Server should be returning 400 here, change this once issue
        // https://github.com/pravega/pravega/issues/531 is fixed.
        assertEquals("Create Stream Status", 500, response.getStatus());
        response.close();

        // Test create stream for non-existent scope
        when(mockControllerService.createStream(any(), anyLong())).thenReturn(createStreamStatus4);
        response = addAuthHeaders(client.target(streamResourceURI).request()).buildPost(Entity.json(createStreamRequest3)).invoke();
        assertEquals("Create Stream Status for non-existent scope", 404, response.getStatus());
        response.close();
    }