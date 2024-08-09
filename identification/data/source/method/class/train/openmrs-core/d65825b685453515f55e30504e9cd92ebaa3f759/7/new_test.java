	@Test
	public void isExpired_shouldReturnFalseForAVoidedOrder() throws Exception {
		Order order = new Order();
		order.setVoided(true);
		order.setDateActivated(DateUtils.parseDate("2014-11-01 11:11:10", DATE_FORMAT));
		order.setAutoExpireDate(DateUtils.parseDate("2014-11-01 11:11:10", DATE_FORMAT));
		assertNull(order.getDateStopped());
		assertFalse(order.isExpired(DateUtils.parseDate("2014-11-01 11:11:12", DATE_FORMAT)));
	}