	@Test
	public void isFuture_shouldReturnFalseForAVoidedOrder() throws Exception {
		Order order = new Order();
		order.setVoided(true);
		order.setDateActivated(DateUtils.parseDate("2014-11-01 11:11:10", DATE_FORMAT));
		assertFalse(order.isStarted(DateUtils.parseDate("2014-11-01 11:11:09", DATE_FORMAT)));
	}