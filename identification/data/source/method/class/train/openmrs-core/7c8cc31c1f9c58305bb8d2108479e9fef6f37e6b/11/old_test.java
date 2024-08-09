	@Test
	public void getOrderFrequency_shouldReturnTheOrderFrequencyThatMatchesTheSpecifiedId() {
		assertEquals("28090760-7c38-11e3-baa7-0800200c9a66", orderService.getOrderFrequency(1).getUuid());
	}