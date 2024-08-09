public void deploy(ApplicationPackage applicationPackage, Map<Version, FileRegistry> fileRegistryMap, 
                       AllocatedHosts allocatedHosts) throws IOException {
        zooKeeperClient.setupZooKeeper();
        zooKeeperClient.feedZooKeeper(applicationPackage);
        zooKeeperClient.feedZKFileRegistries(fileRegistryMap);
        zooKeeperClient.feedProvisionInfo(allocatedHosts);
    }