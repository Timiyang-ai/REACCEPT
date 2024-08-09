	@Test
	public void isBirthdayTest(){
		boolean b = Validator.isBirthday("20150101");
		Assert.assertTrue(b);
		boolean b2 = Validator.isBirthday("2015-01-01");
		Assert.assertTrue(b2);
		boolean b3 = Validator.isBirthday("2015.01.01");
		Assert.assertTrue(b3);
		boolean b4 = Validator.isBirthday("2015年01月01日");
		Assert.assertTrue(b4);
		boolean b5 = Validator.isBirthday("2015.01.01");
		Assert.assertTrue(b5);
		
		//验证年非法
		Assert.assertFalse(Validator.isBirthday("2095.05.01"));
		//验证月非法
		Assert.assertFalse(Validator.isBirthday("2015.13.01"));
		//验证日非法
		Assert.assertFalse(Validator.isBirthday("2015.02.29"));
	}