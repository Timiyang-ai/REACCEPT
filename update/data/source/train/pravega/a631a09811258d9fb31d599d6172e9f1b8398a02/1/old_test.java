@Test
    public void testGetScalingEvents() throws Exception {
        String resourceURI = getURI() + "v1/scopes/scope1/streams/stream1/scaling-events";
        List<ScaleMetadata> scaleMetadataList = new ArrayList<>();

        /* Test to get scaling events

        There are 4 scale events in final list.
        Filter 'from' and 'to' is also tested here.
        Event1 is before 'from'
        Event2 and Event3 are between 'from' and 'to'
        Event4 is after 'to'
        Response contains 3 events : Event1 acts as reference event. Event2 and Event3 fall between 'from' and 'to'.
         */
        Segment segment1 = new Segment(0, System.currentTimeMillis(), 0.00, 0.50);
        Segment segment2 = new Segment(1, System.currentTimeMillis(), 0.50, 1.00);
        List<Segment> segmentList1 = Arrays.asList(segment1, segment2);
        ScaleMetadata scaleMetadata1 = new ScaleMetadata(System.currentTimeMillis() / 2, segmentList1);
        scaleMetadataList.add(scaleMetadata1);

        long fromDateTime = System.currentTimeMillis();

        Segment segment3 = new Segment(0, System.currentTimeMillis(), 0.00, 0.50);
        Segment segment4 = new Segment(1, System.currentTimeMillis(), 0.50, 1.00);
        List<Segment> segmentList2 = Arrays.asList(segment3, segment4);
        ScaleMetadata scaleMetadata2 = new ScaleMetadata(System.currentTimeMillis(), segmentList2);
        scaleMetadataList.add(scaleMetadata2);

        Segment segment5 = new Segment(0, System.currentTimeMillis(), 0.00, 0.25);
        Segment segment6 = new Segment(1, System.currentTimeMillis(), 0.25, 1.00);
        List<Segment> segmentList3 = Arrays.asList(segment5, segment6);
        ScaleMetadata scaleMetadata3 = new ScaleMetadata(System.currentTimeMillis(), segmentList3);
        scaleMetadataList.add(scaleMetadata3);

        long toDateTime = System.currentTimeMillis();

        Segment segment7 = new Segment(0, System.currentTimeMillis(), 0.00, 0.40);
        Segment segment8 = new Segment(0, System.currentTimeMillis(), 0.40, 1.00);
        List<Segment> segmentList4 = Arrays.asList(segment7, segment8);
        ScaleMetadata scaleMetadata4 = new ScaleMetadata(toDateTime * 2, segmentList4);
        scaleMetadataList.add(scaleMetadata4);

        when(mockControllerService.getScaleRecords(scope1, stream1)).
                thenReturn(CompletableFuture.completedFuture(scaleMetadataList));
        Response response = client.target(resourceURI).queryParam("from", fromDateTime).
                queryParam("to", toDateTime).request().buildGet().invoke();
        assertEquals("Get Scaling Events response code", 200, response.getStatus());
        final List<ScaleMetadata> scaleMetadataListResponse = response.readEntity(List.class);
        assertEquals("List Size", 3, scaleMetadataListResponse.size());

        // Test for getScalingEvents for invalid scope/stream.
        final CompletableFuture<List<ScaleMetadata>> completableFuture1 = new CompletableFuture<>();
        completableFuture1.completeExceptionally(new DataNotFoundException(""));
        when(mockControllerService.getScaleRecords("scope1", "stream1")).thenReturn(completableFuture1);
        response = client.target(resourceURI).queryParam("from", fromDateTime).
                queryParam("to", toDateTime).request().buildGet().invoke();
        assertEquals("Get Scaling Events response code", 404, response.getStatus());

        // Test for getScalingEvents for bad request.
        // from > to is tested here
        when(mockControllerService.getScaleRecords("scope1", "stream1")).
                thenReturn(CompletableFuture.completedFuture(scaleMetadataList));
        response = client.target(resourceURI).queryParam("from", fromDateTime * 2).
                queryParam("to", fromDateTime).request().buildGet().invoke();
        assertEquals("Get Scaling Events response code", 400, response.getStatus());

        // Test for getScalingEvents failure.
        final CompletableFuture<List<ScaleMetadata>> completableFuture = new CompletableFuture<>();
        completableFuture.completeExceptionally(new Exception());
        when(mockControllerService.getScaleRecords("scope1", "stream1")).thenReturn(completableFuture);
        response = client.target(resourceURI).request().buildGet().invoke();
        assertEquals("Get Scaling Events response code", 500, response.getStatus());
    }