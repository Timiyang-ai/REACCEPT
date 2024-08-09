	@Test
	public void getLocalhostTest(){
		InetAddress localhost = NetUtil.getLocalhost();
		Assert.assertNotNull(localhost);
	}