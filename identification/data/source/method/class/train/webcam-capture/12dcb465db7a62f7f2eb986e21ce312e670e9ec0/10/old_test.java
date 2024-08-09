	@Test
	public void test_open() {

		EasyMock
			.expect(device.getName())
			.andReturn("HD Mock Device")
			.anyTimes();

		EasyMock
			.expect(device.isOpen())
			.andReturn(false)
			.once();

		EasyMock
			.expect(device.getResolution())
			.andReturn(new Dimension(1024, 768))
			.once();

		device.open();
		EasyMock
			.expectLastCall()
			.once();

		device.dispose();
		EasyMock
			.expectLastCall()
			.anyTimes();

		EasyMock
			.expect(driver.getDevices())
			.andReturn(new ArrayList<WebcamDevice>(Arrays.asList(device)))
			.anyTimes();

		EasyMock
			.expect(driver.isThreadSafe())
			.andReturn(true)
			.anyTimes();

		replayAll();

		Webcam.setDriver(driver);

		Webcam webcam = Webcam.getDefault();
		webcam.open();

		verifyAll();
	}