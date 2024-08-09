	@Test
	public void errorTest(){
		Console.error();
		
		String[] a = {"abc", "bcd", "def"};
		Console.error(a);
		
		Console.error("This is Console error for {}.", "test");
	}