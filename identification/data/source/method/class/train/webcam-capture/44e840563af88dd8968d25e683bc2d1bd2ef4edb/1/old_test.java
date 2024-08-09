	@Test
	public void test_register() throws MalformedURLException {

		IpCamDeviceRegistry.register("test 01", "http://p.de/c=1", IpCamMode.PULL);

		Assert.assertEquals(1, Webcam.getWebcams().size());

		IpCamDeviceRegistry.register("test 02", "http://p.de/c=1", IpCamMode.PULL);
		IpCamDeviceRegistry.register("test 03", "http://p.de/c=1", IpCamMode.PULL);
		IpCamDeviceRegistry.register("test 04", "http://p.de/c=1", IpCamMode.PULL);
		IpCamDeviceRegistry.register("test 05", "http://p.de/c=1", IpCamMode.PULL);

		Assert.assertEquals(5, Webcam.getWebcams().size());
	}