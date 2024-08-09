@Test(dependsOnMethods = "releaseTheSessionBlockingTheGrid")
	public void validate() throws InterruptedException {
		int cpt=0;
		while(cpt <10){
			Thread.sleep(250);
			cpt++;
			try {
				requests.get(0).getTestSession();
			}catch (IllegalAccessError iae){
				//ignore
			}
		}
		System.out.println(">"+requests);
		Assert.assertNotNull(requests.get(0).getTestSession());	
		Assert.assertEquals(requests.get(0).getDesiredCapabilities().get("_priority"), 1);
	}