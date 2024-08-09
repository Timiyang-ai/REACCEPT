	@Test
	public void getHandlersForType_shouldReturnAListOfAllClassesThatCanHandleThePassedType() {
		List<Validator> l = HandlerUtil.getHandlersForType(Validator.class, Order.class);
		Assert.assertEquals(1, l.size());
		Assert.assertEquals(OrderValidator.class, l.iterator().next().getClass());
		l = HandlerUtil.getHandlersForType(Validator.class, DrugOrder.class);
		Assert.assertEquals(2, l.size());
	}