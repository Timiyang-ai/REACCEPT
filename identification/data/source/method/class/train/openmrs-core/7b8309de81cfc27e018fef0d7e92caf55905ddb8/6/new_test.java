@Test
	@Verifies(value = "return list of orders for patient with respect to the include voided flag", method = "getOrdersByPatient(Patient, boolean)")
	public void getOrdersByPatient_shouldReturnListOfOrdersForPatientWithRespectToTheIncludeVoidedFlag() throws Exception {
		executeDataSet(ORDERS_DATASET_XML);
		Patient p = Context.getPatientService().getPatient(2);
		List<Order> orders = Context.getOrderService().getOrdersByPatient(p, true);
		Assert.assertEquals(8, orders.size());
		
		orders = Context.getOrderService().getOrdersByPatient(p, false);
		Assert.assertEquals(4, orders.size());
	}