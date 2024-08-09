	@Test
	public void getRevisionOrder_shouldReturnRevisionOrderIfOrderHasBeenRevised() {
		assertEquals(orderService.getOrder(111), orderService.getRevisionOrder(orderService.getOrder(1)));
	}