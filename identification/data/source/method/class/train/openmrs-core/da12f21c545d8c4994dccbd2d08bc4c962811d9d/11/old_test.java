@Test
	@Verifies(value = "should not allow deleting an order frequency that is in use", method = "purgeOrderFrequency(OrderFrequency)")
	public void purgeOrderFrequency_shouldNotAllowDeletingAnOrderFrequencyThatIsInUse() throws Exception {
		OrderFrequency orderFrequency = orderService.getOrderFrequency(1);
		assertNotNull(orderFrequency);
		
		expectedException.expect(APIException.class);
		expectedException.expectMessage("This order frequency cannot be deleted because it is already in use");
		orderService.purgeOrderFrequency(orderFrequency);
	}