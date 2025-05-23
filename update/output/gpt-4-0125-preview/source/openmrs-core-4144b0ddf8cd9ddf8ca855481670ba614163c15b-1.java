@Test(expected = APIException.class)
	@Verifies(value = "should not allow editing an existing order frequency that is in use", method = "saveOrderFrequency(OrderFrequency)")
	public void saveOrderFrequency_shouldNotAllowEditingAnExistingOrderFrequencyThatIsInUse() throws Exception {
		OrderFrequency orderFrequency = Context.getOrderService().getOrderFrequency(1);
		assertNotNull(orderFrequency);
		
		orderFrequency.setFrequencyPerDay(4d);
		expectedException.expect(APIException.class);
		expectedException.expectMessage("Order.frequency.cannot.edit");
		Context.getOrderService().saveOrderFrequency(orderFrequency);
	}