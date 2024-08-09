@Test
    public void lastReadTimeMillis() {
        TcpIpConnection connAB = connect(connManagerA, addressB);
        TcpIpConnection connBA = connect(connManagerB, addressA);

        // we need to sleep some so that the lastReadTime of the connection gets nice and old.
        // we need this so we can determine the lastReadTime got updated
        sleepSeconds(3);

        Packet packet = new Packet(serializationService.toBytes("foo"));

        connAB.write(packet);

        // wait for the packet to get read.
        assertTrueEventually(new AssertTask() {
            @Override
            public void run() throws Exception {
                assertEquals(1, packetsB.size());
                System.out.println("Packet processed");
            }
        });

        long lastReadTimeMs = connBA.lastReadTimeMillis();
        long nowMs = currentTimeMillis();

        // make sure that the lastWrite time is within the given marginOfErrorMs.
        // if we make this marginOfError very small, there is a high chance of spurious failures
        int marginOfErrorMs = 1000;

        // last read time should be equal or smaller than now.
        assertTrue("nowMs = " + nowMs + " lastReadTimeMs:" + lastReadTimeMs, lastReadTimeMs <= nowMs);
        // last read time should be larger or equal than the now-marginOfError
        assertTrue("nowMs = " + nowMs + " lastReadTimeMs:" + lastReadTimeMs, lastReadTimeMs >= nowMs - marginOfErrorMs);
    }