    private static void setZkProperty(String key, String value) throws Exception {
        // update the underlying zk property and assert that the new value is picked up
        final CountDownLatch updateLatch = new CountDownLatch(1);
        zkConfigSource.addUpdateListener(new WatchedUpdateListener() {
            public void updateConfiguration(WatchedUpdateResult result) {
                updateLatch.countDown();
            }
        });
        zkConfigSource.setZkProperty(key, value);
        updateLatch.await();
    }