	@Test
	public void isValidCardTest(){
		boolean valid = IdcardUtil.isValidCard(ID_18);
		Assert.assertTrue(valid);
		
		boolean valid15 = IdcardUtil.isValidCard(ID_15);
		Assert.assertTrue(valid15);
	}