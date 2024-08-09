	@Test
	public void test_unregister() throws MalformedURLException {

		IpCamDevice d1 = IpCamDeviceRegistry.register("test 01", "http://p.de/c=1", IpCamMode.PULL);
		IpCamDevice d2 = IpCamDeviceRegistry.register("test 02", "http://p.de/c=1", IpCamMode.PULL);
		IpCamDevice d3 = IpCamDeviceRegistry.register("test 03", "http://p.de/c=1", IpCamMode.PULL);
		IpCamDevice d4 = IpCamDeviceRegistry.register("test 04", "http://p.de/c=1", IpCamMode.PULL);
		IpCamDevice d5 = IpCamDeviceRegistry.register("test 05", "http://p.de/c=1", IpCamMode.PULL);

		Assert.assertEquals(5, Webcam.getWebcams().size());

		IpCamDeviceRegistry.unregister(d5); // remove "test 05"
		IpCamDeviceRegistry.unregister(d4); // remove "test 04"
		IpCamDeviceRegistry.unregister(d3); // remove "test 03"
		IpCamDeviceRegistry.unregister(d2); // remove "test 02"
		IpCamDeviceRegistry.unregister(d1); // remove "test 01"

		Assert.assertTrue(Webcam.getWebcams().isEmpty());
	}