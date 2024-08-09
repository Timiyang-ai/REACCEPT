@Test
	@Verifies(value = "should not allow deleting an order frequency that is in use", method = "purgeOrderFrequency(OrderFrequency)")
	public void purgeOrderFrequency_shouldNotAllowDeletingAnOrderFrequencyThatIsInUse() throws Exception {
		OrderFrequency orderFrequency = orderService.getOrderFrequency(1);
		assertNotNull(orderFrequency);
		
		expectedException.expect(CannotDeleteObjectInUseException.class);
		expectedException.expectMessage(mss.getMessage("Order.frequency.cannot.delete"));
		orderService.purgeOrderFrequency(orderFrequency);
	}