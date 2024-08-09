@Test
    public void testGetScalingEvents() throws Exception {
        String resourceURI = getURI() + "v1/scopes/scope1/streams/stream1/scaling-events";
        List<ScaleMetadata> scaleMetadataList = new ArrayList<>();

        /* Test to get scaling events

        There are 5 scale events in final list.
        Filter 'from' and 'to' is also tested here.
        Event1 is before 'from'
        Event2 is before 'from'
        Event3 and Event4 are between 'from' and 'to'
        Event5 is after 'to'
        Response contains 3 events : Event2 acts as reference event. Event3 and Event4 fall between 'from' and 'to'.
         */
        Segment segment1 = new Segment(0, System.currentTimeMillis(), 0.00, 0.50);
        Segment segment2 = new Segment(1, System.currentTimeMillis(), 0.50, 1.00);
        List<Segment> segmentList1 = Arrays.asList(segment1, segment2);
        ScaleMetadata scaleMetadata1 = new ScaleMetadata(System.currentTimeMillis() / 2, segmentList1);

        Segment segment3 = new Segment(2, System.currentTimeMillis(), 0.00, 0.40);
        Segment segment4 = new Segment(3, System.currentTimeMillis(), 0.40, 1.00);
        List<Segment> segmentList2 = Arrays.asList(segment3, segment4);
        ScaleMetadata scaleMetadata2 = new ScaleMetadata(1 + System.currentTimeMillis() / 2, segmentList2);

        long fromDateTime = System.currentTimeMillis();

        Segment segment5 = new Segment(4, System.currentTimeMillis(), 0.00, 0.50);
        Segment segment6 = new Segment(5, System.currentTimeMillis(), 0.50, 1.00);
        List<Segment> segmentList3 = Arrays.asList(segment5, segment6);
        ScaleMetadata scaleMetadata3 = new ScaleMetadata(System.currentTimeMillis(), segmentList3);

        Segment segment7 = new Segment(6, System.currentTimeMillis(), 0.00, 0.25);
        Segment segment8 = new Segment(7, System.currentTimeMillis(), 0.25, 1.00);
        List<Segment> segmentList4 = Arrays.asList(segment7, segment8);
        ScaleMetadata scaleMetadata4 = new ScaleMetadata(System.currentTimeMillis(), segmentList4);

        long toDateTime = System.currentTimeMillis();

        Segment segment9 = new Segment(8, System.currentTimeMillis(), 0.00, 0.40);
        Segment segment10 = new Segment(9, System.currentTimeMillis(), 0.40, 1.00);
        List<Segment> segmentList5 = Arrays.asList(segment9, segment10);
        ScaleMetadata scaleMetadata5 = new ScaleMetadata(toDateTime * 2, segmentList5);

        // HistoryRecords.readAllRecords returns a list of records in decreasing order
        // so we add the elements in reverse order as well to simulate that behavior
        scaleMetadataList.add(scaleMetadata5);
        scaleMetadataList.add(scaleMetadata4);
        scaleMetadataList.add(scaleMetadata3);
        scaleMetadataList.add(scaleMetadata2);
        scaleMetadataList.add(scaleMetadata1);

        when(mockControllerService.getScaleRecords(scope1, stream1)).
                thenReturn(CompletableFuture.completedFuture(scaleMetadataList));
        Response response = addAuthHeaders(client.target(resourceURI).queryParam("from", fromDateTime).
                queryParam("to", toDateTime).request()).buildGet().invoke();
        assertEquals("Get Scaling Events response code", 200, response.getStatus());
        assertTrue(response.bufferEntity());
        List<ScaleMetadata> scaleMetadataListResponse = response.readEntity(
                new GenericType<List<ScaleMetadata>>() { });
        assertEquals("List Size", 3, scaleMetadataListResponse.size());
        scaleMetadataListResponse.forEach(data -> {
            log.warn("Here");
            data.getSegments().forEach( segment -> {
               log.debug("Checking segment number: " + segment.getNumber());
               assertTrue("Event 1 shouldn't be included", segment.getNumber() != 0);
            });
        });

        // Test for large number of scaling events.
        scaleMetadataList.clear();
        scaleMetadataList.addAll(Collections.nCopies(50000, scaleMetadata3));
        when(mockControllerService.getScaleRecords(scope1, stream1)).
                thenReturn(CompletableFuture.completedFuture(scaleMetadataList));
        response = addAuthHeaders(client.target(resourceURI).queryParam("from", fromDateTime).
                queryParam("to", toDateTime).request()).buildGet().invoke();
        assertEquals("Get Scaling Events response code", 200, response.getStatus());
        assertTrue(response.bufferEntity());
        scaleMetadataListResponse = response.readEntity(
                new GenericType<List<ScaleMetadata>>() { });
        assertEquals("List Size", 50000, scaleMetadataListResponse.size());

        // Test for getScalingEvents for invalid scope/stream.
        final CompletableFuture<List<ScaleMetadata>> completableFuture1 = new CompletableFuture<>();
        completableFuture1.completeExceptionally(StoreException.create(StoreException.Type.DATA_NOT_FOUND, "stream1"));
        when(mockControllerService.getScaleRecords("scope1", "stream1")).thenReturn(completableFuture1);
        response = addAuthHeaders(client.target(resourceURI).queryParam("from", fromDateTime).
                queryParam("to", toDateTime).request()).buildGet().invoke();
        assertEquals("Get Scaling Events response code", 404, response.getStatus());

        // Test for getScalingEvents for bad request.
        // from > to is tested here
        when(mockControllerService.getScaleRecords("scope1", "stream1")).
                thenReturn(CompletableFuture.completedFuture(scaleMetadataList));
        response = addAuthHeaders(client.target(resourceURI).queryParam("from", fromDateTime * 2).
                queryParam("to", fromDateTime).request()).buildGet().invoke();
        assertEquals("Get Scaling Events response code", 400, response.getStatus());

        // Test for getScalingEvents failure.
        final CompletableFuture<List<ScaleMetadata>> completableFuture = new CompletableFuture<>();
        completableFuture.completeExceptionally(new Exception());
        when(mockControllerService.getScaleRecords("scope1", "stream1")).thenReturn(completableFuture);
        response = addAuthHeaders(client.target(resourceURI).request()).buildGet().invoke();
        assertEquals("Get Scaling Events response code", 500, response.getStatus());
    }