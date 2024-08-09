public void deploy(ApplicationPackage applicationPackage, Map<Version, FileRegistry> fileRegistryMap, 
                       AllocatedHosts allocatedHosts) throws IOException {
        zooKeeperClient.setupZooKeeper();
        zooKeeperClient.write(applicationPackage);
        zooKeeperClient.write(fileRegistryMap);
        zooKeeperClient.write(allocatedHosts);
    }