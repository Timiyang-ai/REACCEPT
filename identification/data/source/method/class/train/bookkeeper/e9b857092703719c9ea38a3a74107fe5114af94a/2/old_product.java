public void deleteFromZooKeeper(ZooKeeper zk, AbstractConfiguration conf,
                                    BookieSocketAddress address, Version version)
            throws KeeperException, InterruptedException, UnknownHostException {
        if (!(version instanceof LongVersion)) {
            throw new IllegalArgumentException("Invalid version type, expected ZkVersion type");
        }

        String zkPath = getZkPath(conf, address);
        zk.delete(zkPath, (int) ((LongVersion) version).getLongVersion());
        LOG.info("Removed cookie from {} for bookie {}.", conf.getZkLedgersRootPath(), address);
    }