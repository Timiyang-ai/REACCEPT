@Test(dependsOnMethods = "releaseTheSessionBlockingTheGrid")
	public void validate() throws InterruptedException {
		Thread.sleep(250);
		Assert.assertNotNull(requests.get(0).getTestSession());	
		Assert.assertEquals(requests.get(0).getDesiredCapabilities().get("_priority"), 1);
	}