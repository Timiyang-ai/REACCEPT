@Test
    public void createTestStream()
            throws Exception {
        Assert.assertNotNull("Pravega not initialized", localPravega);
        String scope = "Scope";
        String streamName = "Stream";
        int numSegments = 10;

        System.setProperty("io.pravega.tls.enabled", String.valueOf(tlsEnabled));
        System.setProperty("io.pravega.auth.certFile",  "../config/cert.pem");

        @Cleanup
        StreamManager streamManager = StreamManager.create(URI.create(
                localPravega.getInProcPravegaCluster().getControllerURI()),
                new PravegaDefaultCredentials("1111_aaaa", "admin"));

        streamManager.createScope(scope);
        Assert.assertTrue("Stream creation is not successful ",
                streamManager.createStream(scope, streamName, StreamConfiguration.builder()
                                   .scope(scope)
                                   .streamName(streamName)
                                   .scalingPolicy(ScalingPolicy.fixed(numSegments))
                                   .build()));

        log.info("Created stream: " + streamName);
    }