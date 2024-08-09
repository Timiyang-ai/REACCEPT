@Test
    public void networkHostCoreLocal() throws IOException {
        resolveGce("_local_", new NetworkService(Settings.EMPTY).resolveBindHostAddress(NetworkService.DEFAULT_NETWORK_HOST));
    }