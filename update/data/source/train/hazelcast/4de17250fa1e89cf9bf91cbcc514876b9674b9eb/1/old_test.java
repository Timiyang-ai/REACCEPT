@Test
    @Ignore
    @Repeat(100)
    public void lastReadTime() {
        TcpIpConnection connAB = connect(connManagerA, addressB);
        TcpIpConnection connBA = connect(connManagerB, addressA);

        // we need to sleep some so that the lastReadTime of the connection gets nice and old.
        // we need this so we can determine the lastReadTime got updated
        sleepSeconds(5);

        Packet packet = new Packet(serializationService.toBytes("foo"));

        connAB.write(packet);

        // wait for the packet to get written.
        assertTrueEventually(new AssertTask() {
            @Override
            public void run() throws Exception {
                assertEquals(1, packetsB.size());
            }
        });

        long result = connBA.lastReadTime();

        long current = System.currentTimeMillis();

        // make sure that the lastWrite time is within the given marginOfErrorMs.
        // if we make this marginOfError very small, there is a high chance of spurious failures
        final int marginOfErrorMs = 1000;
        assertTrue(current + marginOfErrorMs > result);
        assertTrue(current - marginOfErrorMs < result);
    }