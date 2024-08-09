@Test
    public void createTestStream()
            throws Exception {
        Assert.assertNotNull("Pravega not initialized", localPravega);
        String scope = "Scope";
        String streamName = "Stream";
        int numSegments = 10;

        ClientConfig clientConfig = ClientConfig.builder()
                                                .controllerURI(URI.create(localPravega.getInProcPravegaCluster().getControllerURI()))
                                                .credentials(new DefaultCredentials("1111_aaaa", "admin"))
                                                .trustStore("../config/cert.pem")
                                                .validateHostName(false)
                                                .build();
        @Cleanup
        StreamManager streamManager = StreamManager.create(clientConfig);

        streamManager.createScope(scope);
        Assert.assertTrue("Stream creation is not successful ",
                streamManager.createStream(scope, streamName, StreamConfiguration.builder()
                                   .scalingPolicy(ScalingPolicy.fixed(numSegments))
                                   .build()));
        log.info("Created stream: " + streamName);

        EventStreamClientFactory clientFactory = EventStreamClientFactory.withScope(scope, clientConfig);
        EventStreamWriter<String> writer = clientFactory.createEventWriter(streamName,
                new JavaSerializer<String>(),
                EventWriterConfig.builder().build());
        log.info("Created writer for stream: " + streamName);

        writer.writeEvent("hello").get();
        log.info("Wrote data to the stream");
    }