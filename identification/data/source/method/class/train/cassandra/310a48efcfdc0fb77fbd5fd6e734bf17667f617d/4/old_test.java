    @Test
    public void execute_HappyPath()
    {
        Sink sink = new Sink(true, true, peers);
        MessagingService.instance().outboundSink.add(sink);
        Assert.assertTrue(localQuorumConnectivityChecker.execute(peers, this::getDatacenter));
    }