@Test
    public void testPutBucketMaxAge() {
    	String[] buckets = new String[]{TestConfig.testBucket_z0, TestConfig.testBucket_na0};
    	for (String bucket : buckets) {
    		final long maxAges[] = {Integer.MIN_VALUE, -54321, -1, 0, 1, 8, 1234567, 11111111, Integer.MAX_VALUE};
    		try {
    			for (long maxAge : maxAges) {
    				// 设置max-age
    				Response response = bucketManager.putBucketMaxAge(bucket, maxAge);
    				Assert.assertEquals(200, response.statusCode);
    				// 获取max-age
    				BucketInfo bucketInfo = bucketManager.getBucketInfo(bucket);
    				long expect = maxAge;
    				long actual = bucketInfo.getMaxAge();
            		System.out.println("expect=" + expect);
            		System.out.println("actual=" + actual);
            		Assert.assertEquals(expect, actual);
    			}
    		} catch (QiniuException e) {
    			Assert.fail(e.response.toString());
    		}
    	}
    }