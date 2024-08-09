	@Test
	public void purgeOrderFrequency_shouldDeleteGivenOrderFrequency() {
		OrderFrequency orderFrequency = orderService.getOrderFrequency(3);
		assertNotNull(orderFrequency);
		
		orderService.purgeOrderFrequency(orderFrequency);
		
		orderFrequency = orderService.getOrderFrequency(3);
		Assert.assertNull(orderFrequency);
		
		//Should reduce the existing number of order frequencies.
		assertEquals(2, orderService.getOrderFrequencies(true).size());
	}