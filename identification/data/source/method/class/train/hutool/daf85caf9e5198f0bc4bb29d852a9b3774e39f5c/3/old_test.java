	@Test
	public void convert15To18Test(){
		String convert15To18 = IdcardUtil.convert15To18(ID_15);
		Assert.assertEquals(convert15To18, "150102198807303035");
	}