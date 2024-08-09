@Test
    public void testPutReferAntiLeech() {
    	String[] buckets = new String[]{TestConfig.testBucket_z0, TestConfig.testBucket_na0};
    	for (String bucket : buckets) {
        	BucketReferAntiLeech leech = new BucketReferAntiLeech();
        	Response response;
        	BucketInfo bucketInfo;
        	try {
        		// 测试白名单
        		leech.setMode(1);
        		leech.setPattern("www.qiniu.com");
        		leech.setAllowEmptyReferer(false);
        		System.out.println(leech.asQueryString());
        		response = bucketManager.putReferAntiLeech(bucket, leech);
        		Assert.assertEquals(200, response.statusCode);
        		bucketInfo = bucketManager.getBucketInfo(bucket);
        		Assert.assertEquals(1, bucketInfo.getAntiLeechMode());
        		Assert.assertArrayEquals((new String[] {"www.qiniu.com"}), bucketInfo.getReferWhite());
        		Assert.assertEquals(false, bucketInfo.isNoRefer());
        		
        		// 测试黑名单
        		leech.setMode(2);
        		leech.setPattern("www.baidu.com");
        		leech.setAllowEmptyReferer(true);
        		System.out.println(leech.asQueryString());
        		response = bucketManager.putReferAntiLeech(bucket, leech);
        		Assert.assertEquals(200, response.statusCode);
        		bucketInfo = bucketManager.getBucketInfo(bucket);
        		Assert.assertEquals(2, bucketInfo.getAntiLeechMode());
        		Assert.assertArrayEquals((new String[] {"www.baidu.com"}), bucketInfo.getReferBlack());
        		Assert.assertEquals(true, bucketInfo.isNoRefer());
        		
        		// 测试关闭
        		leech = new BucketReferAntiLeech();
        		System.out.println(leech.asQueryString());
        		response = bucketManager.putReferAntiLeech(bucket, leech);
        		Assert.assertEquals(200, response.statusCode);
        		bucketInfo = bucketManager.getBucketInfo(bucket);
        		Assert.assertEquals(0, bucketInfo.getAntiLeechMode());
        		Assert.assertNull("ReferBlack should be Null", bucketInfo.getReferBlack());
        		Assert.assertNull("ReferWhtie should be Null", bucketInfo.getReferWhite());
        		Assert.assertEquals(false, bucketInfo.isNoRefer());
        		
    		} catch (Exception e) {
    			if (e instanceof QiniuException) {
    				QiniuException ex = (QiniuException) e;
    				Assert.fail(ex.response.toString());
    			}
    		}
    	}
    }