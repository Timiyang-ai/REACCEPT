synchronized void setZkProperty(String key, String value) throws Exception {
        final CountDownLatch updateLatch = new CountDownLatch(1);
       
        final String path = configRootPath + "/" + key; 

        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            public void childEvent(final CuratorFramework client, final PathChildrenCacheEvent event) throws Exception {
                if (event.getData().getPath().equals(path) && 
                        (event.getType() == Type.CHILD_ADDED || event.getType() == Type.CHILD_UPDATED)) {

                    // sync the config for immediate test-assertion purposes
                    syncConfig(client, pathChildrenCache, configRootPath);

                    logger.info("flipping latch after event [{}] with [{}] count remaining.", event, updateLatch.getCount());
                    updateLatch.countDown();
                }
            }
        };
        
        // add temporary listener needed to block until this update takes effect
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);        

        byte[] data = value.getBytes(charset);
        
        try {
            // attempt to create (intentionally doing this instead of checkExists()) 
            client.create().creatingParentsIfNeeded().forPath(path, data);
        } catch (NodeExistsException exc) {
            // key already exists - update the data instead
            client.setData().forPath(path, data);
        }
        
        try {
            updateLatch.await();
        } catch (final InterruptedException exc) {
            logger.error("interrupted while waiting on latch [{}]", updateLatch, exc);
            throw new Exception("interrupted while waiting on latch [" + updateLatch + "]", exc);
        }

        // remove temporary listener
        pathChildrenCache.getListenable().removeListener(pathChildrenCacheListener);
    }