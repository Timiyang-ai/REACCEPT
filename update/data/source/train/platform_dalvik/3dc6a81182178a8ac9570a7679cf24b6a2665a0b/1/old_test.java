@TestTargetNew(
        level = TestLevel.PARTIAL_COMPLETE,
        notes = "",
        method = "socket",
        args = {}
    )
    public void testSocket_BasicStatusBeforeConnect() throws SocketException {
        assertFalse(this.channel1.isConnected());// not connected
        DatagramSocket s1 = this.channel1.socket();
        assertSocketBeforeConnect(s1);
        DatagramSocket s2 = this.channel1.socket();
        // same
        assertSame(s1, s2);
    }