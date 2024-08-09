	@Test
	public void getDiscontinuationOrder_shouldReturnDiscontinuationOrderIfOrderHasBeenDiscontinued() {
		Order order = orderService.getOrder(111);
		Order discontinuationOrder = orderService.discontinueOrder(order, "no reason", new Date(),
		    providerService.getProvider(1), order.getEncounter());
		
		Order foundDiscontinuationOrder = orderService.getDiscontinuationOrder(order);
		
		assertThat(foundDiscontinuationOrder, is(discontinuationOrder));
	}