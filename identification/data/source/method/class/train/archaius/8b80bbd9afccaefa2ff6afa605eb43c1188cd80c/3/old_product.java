synchronized void setZkProperty(String key, String value) throws Exception {
        final CountDownLatch updateLatch = new CountDownLatch(1);
        
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            public void childEvent(final CuratorFramework client, final PathChildrenCacheEvent event) throws Exception {
                ZooKeeperConfigurationSource.syncConfig(client, pathChildrenCache, configRootPath);
                if (updateLatch != null && updateLatch.getCount() > 0) { 
                    logger.trace("flipping latch after event [{}] with [{}] count remaining.", event, updateLatch.getCount());
                    updateLatch.countDown();
                }
            }
        };
        
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);        

        byte[] data = value.getBytes(Charset.forName("UTF-8"));
        try {
            client.create().creatingParentsIfNeeded().forPath(configRootPath + "/" + key, data);
        } catch (NodeExistsException exc) {
            client.setData().forPath(configRootPath + "/" + key, data);              
        } catch (Exception exc) {
            updateLatch.countDown();
        }
        updateLatch.await();

        pathChildrenCache.getListenable().removeListener(pathChildrenCacheListener);
    }