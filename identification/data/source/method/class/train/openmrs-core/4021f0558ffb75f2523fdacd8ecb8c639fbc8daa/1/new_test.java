	@Test
	public void checkScheduleOverlap_shouldReturnTrueIfOrder1AndOrder2DoNotHaveEndDate() {
		Date date = new Date();
		Order order1 = new Order();
		order1.setScheduledDate(DateUtils.addDays(date, 4)); //Order1 scheduled after 4 days without stop date
		order1.setUrgency(Order.Urgency.ON_SCHEDULED_DATE);
		
		Order order2 = new Order();
		order2.setDateActivated(date);
		order2.setScheduledDate(DateUtils.addDays(date, 6)); //Order2 scheduled after 6 days without stop date
		order2.setUrgency(Order.Urgency.ON_SCHEDULED_DATE);
		
		assertTrue(OrderUtil.checkScheduleOverlap(order1, order2));
		assertTrue(OrderUtil.checkScheduleOverlap(order2, order1)); //Checks vice versa
	}