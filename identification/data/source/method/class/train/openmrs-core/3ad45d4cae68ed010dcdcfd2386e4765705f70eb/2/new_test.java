@Test
	public void isActive_shouldReturnFalseForAVoidedOrder() throws Exception {
		Order order = new Order();
		order.setVoided(true);
		order.setDateActivated(DateUtils.parseDate("2014-11-01 11:11:10", DATE_FORMAT));
		assertNull(order.getDateStopped());
		assertNull(order.getAutoExpireDate());
		assertFalse(order.isActive(DateUtils.parseDate("2014-11-01 11:11:11", DATE_FORMAT)));
	}