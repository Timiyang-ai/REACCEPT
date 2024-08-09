@Test
    public void testPutBucketAccessMode() {
    	String[] buckets = new String[]{TestConfig.testBucket_z0, TestConfig.testBucket_na0};
    	for (String bucket : buckets) {
        	Response response;
            try {
            	// 测试转私有空间
            	response = bucketManager.putBucketAccessMode(bucket, AclType.PRIVATE);
            	Assert.assertEquals(200, response.statusCode);
                BucketInfo info = bucketManager.getBucketInfo(bucket);
                Assert.assertEquals(1, info.getPrivate());

                // 测试转公有空间
                response = bucketManager.putBucketAccessMode(bucket, AclType.PUBLIC);
                Assert.assertEquals(200, response.statusCode);
                info = bucketManager.getBucketInfo(bucket);
                Assert.assertEquals(0, info.getPrivate());
                
            } catch (QiniuException e) {
            	Assert.fail(e.response.toString());
            }
    	}
    	// 测试空间不存在情况
        try {
        	bucketManager.putBucketAccessMode(TestConfig.dummyBucket, AclType.PRIVATE);
        } catch (QiniuException e) {
            Assert.assertEquals(631, e.response.statusCode);
        }
    }