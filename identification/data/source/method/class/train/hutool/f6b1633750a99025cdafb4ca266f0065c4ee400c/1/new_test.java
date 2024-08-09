	@Test
	public void getBirthByIdCardTest(){
		String birth = IdcardUtil.getBirthByIdCard(ID_18);
		Assert.assertEquals(birth, "19781216");
		
		String birth2 = IdcardUtil.getBirthByIdCard(ID_15);
		Assert.assertEquals(birth2, "19880730");
	}