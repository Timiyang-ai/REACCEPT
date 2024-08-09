public void networkHostCoreLocal() throws IOException {
        resolveGce("_local_", new NetworkService(Settings.EMPTY).resolveBindHostAddresses(new String[] { NetworkService.DEFAULT_NETWORK_HOST }));
    }