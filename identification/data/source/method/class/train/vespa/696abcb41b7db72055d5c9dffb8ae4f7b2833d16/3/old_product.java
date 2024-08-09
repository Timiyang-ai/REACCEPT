public void create(Path path) {
        if (exists(path)) return;

        String absolutePath = path.getAbsolute();
        try {
            framework().create().creatingParentsIfNeeded().forPath(absolutePath);
        } catch (org.apache.zookeeper.KeeperException.NodeExistsException e) {
            // Path created between exists() and create() call, do nothing
        } catch (Exception e) {
            throw new RuntimeException("Could not create " + absolutePath, e);
        }
    }