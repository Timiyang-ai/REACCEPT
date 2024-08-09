    @Test
    public void addBannedAddress() throws UnknownHostException {
        InetAddress address = generateIPAddressV4();
        PeerScoringManager manager = createPeerScoringManager();

        manager.banAddress(address);
        Assert.assertFalse(manager.hasGoodReputation(address));
    }