	@Test
	public void getOrders_shouldFailIfPatientIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Patient is required");
		orderService.getOrders(null, null, null, false);
	}