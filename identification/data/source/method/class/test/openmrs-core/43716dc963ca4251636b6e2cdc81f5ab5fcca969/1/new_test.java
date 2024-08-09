@Test
	@Verifies(value = "should properly set the date", method = "setDate(int,int,int,null,null,null)")
	public void setDate_shouldProperlySetTheDate() throws Exception {
		//TODO auto-generated
		ApproximateDate approximateDate = new ApproximateDate();
		approximateDate.setDate(1988, 9, 15, false, false, false);
		Calendar cal = Calendar.getInstance();
		cal.set(1988, 9, 15);
		//		cal.ge
		Assert.fail("Not yet implemented");
	}