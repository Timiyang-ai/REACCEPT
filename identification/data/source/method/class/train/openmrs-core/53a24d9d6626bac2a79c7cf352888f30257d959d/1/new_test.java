	@Test
	public void getAllOrdersByPatient_shouldFailIfPatientIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Patient is required");
		orderService.getAllOrdersByPatient(null);
	}