@Test
	@Verifies(value = "should return properly estimated dates", method = "getDate()")
	public void getDate_shouldReturnProperlyEstimatedDates() throws Exception {
		//TODO auto-generated
		ApproximateDate approximateDate = new ApproximateDate();
		approximateDate.setYear(2009);
		approximateDate.setApproximated(ApproximateDate.APPROXIMATE_YEAR);
		Date d = approximateDate.getDate();
		Date c = approximateDate.getDate();
	}