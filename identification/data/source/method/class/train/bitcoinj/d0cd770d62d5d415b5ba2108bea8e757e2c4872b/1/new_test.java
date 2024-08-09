    @Test
    public void getPeer_one() throws Exception{
        SeedPeers seedPeers = new SeedPeers(MAINNET);
        assertThat(seedPeers.getPeer(), notNullValue());
    }