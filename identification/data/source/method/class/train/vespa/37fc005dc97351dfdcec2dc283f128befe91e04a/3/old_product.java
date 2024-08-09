public void deploy(ApplicationPackage applicationPackage, Map<Version, FileRegistry> fileRegistryMap, 
                       ProvisionInfo provisionInfo) throws IOException {
        zooKeeperClient.setupZooKeeper();
        zooKeeperClient.feedZooKeeper(applicationPackage);
        zooKeeperClient.feedZKFileRegistries(fileRegistryMap);
        zooKeeperClient.feedProvisionInfo(provisionInfo);
    }