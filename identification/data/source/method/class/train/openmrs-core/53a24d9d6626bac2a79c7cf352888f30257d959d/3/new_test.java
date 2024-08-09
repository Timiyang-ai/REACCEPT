	@Test
	public void getOrderTypeByConcept_shouldGetOrderTypeMappedToTheGivenConcept() {
		OrderType orderType = orderService.getOrderTypeByConcept(Context.getConceptService().getConcept(5089));
		
		Assert.assertNotNull(orderType);
		Assert.assertEquals(2, orderType.getOrderTypeId().intValue());
	}