	@Test
	public void getOrderFrequencyByUuid_shouldReturnTheOrderFrequencyThatMatchesTheSpecifiedUuid() {
		assertEquals(1, orderService.getOrderFrequencyByUuid("28090760-7c38-11e3-baa7-0800200c9a66").getOrderFrequencyId()
		        .intValue());
	}