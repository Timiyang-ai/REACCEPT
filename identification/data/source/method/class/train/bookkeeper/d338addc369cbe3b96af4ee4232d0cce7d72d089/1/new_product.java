void writeToZooKeeper(ZooKeeper zk, ServerConfiguration conf, Version version)
            throws KeeperException, InterruptedException, UnknownHostException {
        List<ACL> zkAcls = ZkUtils.getACLs(conf);
        String bookieCookiePath = conf.getZkLedgersRootPath() + "/"
                + BookKeeperConstants.COOKIE_NODE;
        String zkPath = getZkPath(conf);
        byte[] data = toString().getBytes(UTF_8);
        if (Version.NEW == version) {
            if (zk.exists(bookieCookiePath, false) == null) {
                try {
                    zk.create(bookieCookiePath, new byte[0],
                            zkAcls, CreateMode.PERSISTENT);
                } catch (KeeperException.NodeExistsException nne) {
                    LOG.info("More than one bookie tried to create {} at once. Safe to ignore",
                            bookieCookiePath);
                }
            }
            zk.create(zkPath, data,
                    zkAcls, CreateMode.PERSISTENT);
        } else {
            if (!(version instanceof LongVersion)) {
                throw new IllegalArgumentException("Invalid version type, expected ZkVersion type");
            }
            zk.setData(zkPath, data, (int) ((LongVersion) version).getLongVersion());
        }
    }