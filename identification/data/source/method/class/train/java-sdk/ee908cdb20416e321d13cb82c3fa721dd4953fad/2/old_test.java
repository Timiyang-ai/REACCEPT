@Test
    public void testPutBucketAccessStyleMode() {
    	Client client = new Client();
    	Response response;
    	try {
    		response = bucketManager.putBucketAccessStyleMode(TestConfig.testBucket_z0, AccessStyleMode.OPEN);
    		Assert.assertEquals(200, response.statusCode);
    		
    		try {
        		client.get(TestConfig.testUrl_z0);
    		} catch (QiniuException e) {
    			e.printStackTrace();
    			Assert.assertEquals(401, e.response.statusCode);
    		}
    		
    		response = bucketManager.putBucketAccessStyleMode(TestConfig.testBucket_z0, AccessStyleMode.CLOSE);
    		Assert.assertEquals(200, response.statusCode);
    		
    		response = client.get(TestConfig.testUrl_z0);
    		Assert.assertEquals(200, response.statusCode);
    		
    	} catch (QiniuException e) {
    		Assert.fail(e.response.toString());
		}
    }