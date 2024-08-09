	@Test
	public void isCitizenIdTest(){
		boolean b = Validator.isCitizenId("150218199012123389");
		Assert.assertTrue(b);
	}