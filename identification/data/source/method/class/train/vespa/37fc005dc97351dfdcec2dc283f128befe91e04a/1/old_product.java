public void deploy(ApplicationPackage applicationPackage, Map<Version, FileRegistry> fileRegistryMap, Map<Version, ProvisionInfo> provisionInfoMap) throws IOException {
        zooKeeperClient.setupZooKeeper();
        zooKeeperClient.feedZooKeeper(applicationPackage);
        zooKeeperClient.feedZKFileRegistries(fileRegistryMap);
        zooKeeperClient.feedProvisionInfos(provisionInfoMap);
    }