	@Test
	public void formatTest(){
		//通常使用
		String result1 = StrFormatter.format("this is {} for {}", "a", "b");
		Assert.assertEquals("this is a for b", result1);
		
		//转义{}
		String result2 = StrFormatter.format("this is \\{} for {}", "a", "b");
		Assert.assertEquals("this is {} for a", result2);
		
		//转义\
		String result3 = StrFormatter.format("this is \\\\{} for {}", "a", "b");
		Assert.assertEquals("this is \\a for b", result3);
	}