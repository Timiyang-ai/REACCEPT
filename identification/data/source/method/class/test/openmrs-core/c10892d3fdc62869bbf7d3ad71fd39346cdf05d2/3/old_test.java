	@Test
	public void saveOrder_shouldNotSaveOrderIfOrderDoesntValidate() {
		Order order = new Order();
		order.setPatient(null);
		order.setOrderer(null);
		expectedException.expect(APIException.class);
		expectedException.expectMessage("failed to validate with reason:");
		orderService.saveOrder(order, null);
	}