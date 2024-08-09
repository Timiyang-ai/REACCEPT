    @Test
    public void getLeader() throws UnknownHostException {
        Consul consul = Consul.consul();
        String ipAndPort = consul.status().getLeader();
        assertLocalIpAndCorrectPort(ipAndPort);

    }