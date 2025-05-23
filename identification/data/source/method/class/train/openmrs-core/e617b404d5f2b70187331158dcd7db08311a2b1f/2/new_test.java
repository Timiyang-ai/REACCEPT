@Test
	@Verifies(value = "return list of non voided orders for patient", method = "getOrdersByPatient(Patient)")
	public void getOrdersByPatient_shouldReturnListOfNonVoidedOrdersForPatient() throws Exception {
		Patient p = Context.getPatientService().getPatient(2);
		List<Order> orders = Context.getOrderService().getOrdersByPatient(p);
		Assert.assertEquals(8, orders.size());
	}