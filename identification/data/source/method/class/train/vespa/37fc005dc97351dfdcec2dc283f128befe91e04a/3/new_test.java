    public void deploy(ApplicationPackage applicationPackage, ConfigCurator configCurator, Path appPath) throws IOException {
        MockDeployLogger logger = new MockDeployLogger();
        ZooKeeperClient client = new ZooKeeperClient(configCurator, logger, true, appPath);
        ZooKeeperDeployer deployer = new ZooKeeperDeployer(client);

        deployer.deploy(applicationPackage, Collections.singletonMap(new Version(1, 0, 0), new MockFileRegistry()), AllocatedHosts.withHosts(Collections.emptySet()));
        assertTrue(configCurator.exists(appPath.getAbsolute()));
    }