    @Test
    public void getSummariesStream() {
        answer = true;
        AmazonS3Client client = mock(AmazonS3Client.class);

        ObjectListing objectListing = mock(ObjectListing.class);

        when(objectListing.getObjectSummaries()).thenReturn(createSummaries());
        when(client.listObjects(any(ListObjectsRequest.class))).thenReturn(objectListing);
        when(objectListing.isTruncated()).thenAnswer(__ -> {
            try {
                return answer;
            } finally {
                answer = false;
            }
        });
        // when(objectListing.getObjectSummaries()).thenReturn(summaries);

        S3Utils utils = new S3Utils(
                                    client, null, null, false, null);
        verify(objectListing, times(0)).getObjectSummaries();
        Stream<String> stream = utils.getSummariesStream(new ListObjectsRequest(), s -> {
            return s.getKey();
        });

        assertEquals(500, stream.limit(500)
                                .count());

        verify(objectListing, times(1)).getObjectSummaries();

    }