@Test
    public void testCopy() {
        Map<String, String> bucketKeyMap = new HashMap<String, String>();
        bucketKeyMap.put(TestConfig.testBucket_z0, TestConfig.testKey_z0);
        bucketKeyMap.put(TestConfig.testBucket_na0, TestConfig.testKey_na0);
        for (Map.Entry<String, String> entry : bucketKeyMap.entrySet()) {
            String bucket = entry.getKey();
            String key = entry.getValue();
            String copyToKey = "copyTo" + Math.random();
            try {
                bucketManager.copy(bucket, key, bucket, copyToKey);
                bucketManager.delete(bucket, copyToKey);
            } catch (QiniuException e) {
                Assert.fail(bucket + ":" + key + "==> " + e.response.toString());
            }
        }
    }