	@Test
	public void getOrderTypeByConceptClass_shouldGetOrderTypeMappedToTheGivenConceptClass() {
		OrderType orderType = orderService.getOrderTypeByConceptClass(Context.getConceptService().getConceptClass(1));
		
		Assert.assertNotNull(orderType);
		Assert.assertEquals(2, orderType.getOrderTypeId().intValue());
	}