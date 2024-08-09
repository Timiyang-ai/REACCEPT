@Test
    public void testPutBucketAccessStyleMode() {
    	String[] buckets = new String[]{TestConfig.testBucket_z0, TestConfig.testBucket_na0};
    	String[] urls = new String[]{TestConfig.testUrl_z0, TestConfig.testUrl_na0};
    	for (int i = 0; i < buckets.length; i ++) {
    		String bucket = buckets[i];
    		String url = urls[i];
        	Client client = new Client();
        	Response response;
        	try {
        		// 测试开启原图保护
        		response = bucketManager.putBucketAccessStyleMode(bucket, AccessStyleMode.OPEN);
        		Assert.assertEquals(200, response.statusCode);
        		try {
            		client.get(url);
        		} catch (QiniuException e) {
        			Assert.assertEquals(401, e.response.statusCode);
        		}
        		
        		// 测试关闭原图保护
        		response = bucketManager.putBucketAccessStyleMode(bucket, AccessStyleMode.CLOSE);
        		Assert.assertEquals(200, response.statusCode);
        		response = client.get(url);
        		Assert.assertEquals(200, response.statusCode);
        		
        	} catch (QiniuException e) {
        		Assert.fail(e.response.toString());
    		}
    	}
    }