@Test
    public void networkHostCoreLocal() throws IOException {
        Settings nodeSettings = Settings.builder()
                .put("network.host", "_local_")
                .build();

        NetworkService networkService = new NetworkService(nodeSettings);
        networkService.addCustomNameResolver(new Ec2NameResolver(nodeSettings));
        InetAddress[] addresses = networkService.resolveBindHostAddress(null);
        assertThat(addresses, arrayContaining(networkService.resolveBindHostAddress("_local_")));
    }