    @Test
    public void connect() throws IOException, InterruptedException {
        TestPinpointServerAcceptor testPinpointServerAcceptor = new TestPinpointServerAcceptor();
        int bindPort = testPinpointServerAcceptor.bind();

        try {
            PinpointClient client = clientFactory.connect("127.0.0.1", bindPort);
            PinpointRPCTestUtils.close(client);
        } finally {
            testPinpointServerAcceptor.close();
        }
    }