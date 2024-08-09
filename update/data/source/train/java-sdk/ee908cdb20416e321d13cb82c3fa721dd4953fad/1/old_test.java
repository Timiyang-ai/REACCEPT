@Test
 // TODO
    public void testPutReferAntiLeech() {
    	BucketReferAntiLeech leech = new BucketReferAntiLeech();
    	Response response;
    	try {
    		System.out.println(leech.asQueryString());
    		response = bucketManager.putReferAntiLeech(TestConfig.testBucket_z0, leech);
    		Assert.assertEquals(200, response.statusCode);
    		
    		leech.setAllowEmptyReferer(false);
    		System.out.println(leech.asQueryString());
    		response = bucketManager.putReferAntiLeech(TestConfig.testBucket_z0, leech);
    		Assert.assertEquals(200, response.statusCode);
    		
    		leech.setAllowEmptyReferer(false);
    		leech.setMode(1);
    		leech.setPattern("www.qiniu.com");
    		System.out.println(leech.asQueryString());
    		response = bucketManager.putReferAntiLeech(TestConfig.testBucket_z0, leech);
    		Assert.assertEquals(200, response.statusCode);
    		System.out.println(response.url());
    		System.out.println(response.reqId);
    		
		} catch (Exception e) {
			if (e instanceof QiniuException) {
				QiniuException ex = (QiniuException) e;
				Assert.fail(ex.response.toString());
			}
		}
    }