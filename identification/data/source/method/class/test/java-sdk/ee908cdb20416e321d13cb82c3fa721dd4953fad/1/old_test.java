@Test
    // TODO
    public void testPutBucketMaxAge() {
    	Client client = new Client();
    	Response response;
    	try {
    		long maxAges[] = {Integer.MIN_VALUE, -54321, -1, 0, 1, 8, 1234567, Integer.MAX_VALUE};
    		for (int i = 0; i < maxAges.length; i ++) {
    			long maxAge = maxAges[i];
    			System.out.println("maxAge=" + maxAge);
    			response = bucketManager.putBucketMaxAge(TestConfig.testBucket_z0, maxAge);
    			Assert.assertEquals(200, response.statusCode);
    			
        		response = client.get(TestConfig.testUrl_z0);
        		String value = respHeader(TestConfig.testUrl_z0, "Cache-Control");
        		System.out.println(value);
//        		if (maxAge <= 0) {
//        			Assert.assertEquals(31536000, value);
//        		} else {
//        			Assert.assertEquals(maxAge, value);
//        		}
    		}
    	} catch (IOException e) {
    		if (e instanceof QiniuException) {
    			Assert.fail(((QiniuException) e).response.toString());
    		}
    	}
    }