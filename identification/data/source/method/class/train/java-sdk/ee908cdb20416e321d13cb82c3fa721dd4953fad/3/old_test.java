@Test
    public void testPutBucketAccessMode() {
    	Response response;
        try {
        	response = bucketManager.putBucketAccessMode(TestConfig.testBucket_z0, AclType.PRIVATE);
        	Assert.assertEquals(200, response.statusCode);
            BucketInfo info = bucketManager.getBucketInfo(TestConfig.testBucket_z0);
            Assert.assertEquals(1, info.getPrivate());

            response = bucketManager.putBucketAccessMode(TestConfig.testBucket_z0, AclType.PUBLIC);
            Assert.assertEquals(200, response.statusCode);
            info = bucketManager.getBucketInfo(TestConfig.testBucket_z0);
            Assert.assertEquals(0, info.getPrivate());
        } catch (QiniuException e) {
        	Assert.fail(e.response.toString());
        }
        try {
        	bucketManager.putBucketAccessMode(TestConfig.dummyBucket, AclType.PRIVATE);
        } catch (QiniuException e) {
            Assert.assertEquals(631, e.response.statusCode);
        }
    }