    @Test
    public void getPeers_length() throws Exception{
        SeedPeers seedPeers = new SeedPeers(MAINNET);
        InetSocketAddress[] addresses = seedPeers.getPeers(0, 0, TimeUnit.SECONDS);
        assertThat(addresses.length, equalTo(MAINNET.getAddrSeeds().length));
    }