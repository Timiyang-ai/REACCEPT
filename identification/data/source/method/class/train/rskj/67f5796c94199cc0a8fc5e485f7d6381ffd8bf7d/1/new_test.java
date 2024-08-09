    @Test
    public void addBannedAddressBlock() throws UnknownHostException {
        InetAddress address = generateIPAddressV4();
        InetAddressBlock addressBlock = new InetAddressBlock(address, 8);

        PeerScoringManager manager = createPeerScoringManager();

        manager.banAddressBlock(addressBlock);
        Assert.assertFalse(manager.hasGoodReputation(address));
    }