@Test
	@Verifies(value = "should not allow deleting an order frequency that is in use", method = "purgeOrderFrequency(OrderFrequency)")
	public void purgeOrderFrequency_shouldNotAllowDeletingAnOrderFrequencyThatIsInUse() throws Exception {
		OrderFrequency orderFrequency = orderService.getOrderFrequency(1);
		assertNotNull(orderFrequency);
		
		expectedException.expect(APIException.class);
		expectedException.expectMessage("Order.frequency.cannot.delete");
		orderService.purgeOrderFrequency(orderFrequency);
	}