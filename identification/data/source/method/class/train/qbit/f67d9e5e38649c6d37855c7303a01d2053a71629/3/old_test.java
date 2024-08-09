    @Test
    public void getPeers() throws UnknownHostException {
        Consul consul = Consul.consul();
        List<String> peers = consul.status().getPeers();
        for (String ipAndPort : peers) {
            assertLocalIpAndCorrectPort(ipAndPort);
        }
    }