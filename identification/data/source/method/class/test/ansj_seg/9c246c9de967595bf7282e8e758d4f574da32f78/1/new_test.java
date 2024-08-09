	@Test
	public void insertTest() {
		DicLibrary.insert(DicLibrary.DEFAULT, "增加新词", "我是词性", 1000);
		Result parse = DicAnalysis.parse("这是用户自定义词典增加新词的例子");
		System.out.println(parse);
		boolean flag = false;
		for (Term term : parse) {
			flag = flag || "增加新词".equals(term.getName());
		}
		Assert.assertTrue(flag);

	}