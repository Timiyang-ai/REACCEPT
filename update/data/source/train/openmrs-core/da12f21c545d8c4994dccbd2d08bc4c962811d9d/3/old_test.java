@Test
	public void isExpired_shouldFailIfDateStoppedIsAfterAutoExpireDate() throws Exception {
		Order order = new Order();
		order.setDateActivated(DateUtils.parseDate("2014-11-01 11:11:10", DATE_FORMAT));
		order.setAutoExpireDate(DateUtils.parseDate("2014-11-01 11:11:11", DATE_FORMAT));
		OrderUtilTest.setDateStopped(order, DateUtils.parseDate("2014-11-01 11:11:12", DATE_FORMAT));
		expectedException.expect(APIException.class);
		expectedException.expectMessage("The order has invalid dateStopped and autoExpireDate values");
		order.isExpired(DateUtils.parseDate("2014-11-01 11:11:13", DATE_FORMAT));
	}