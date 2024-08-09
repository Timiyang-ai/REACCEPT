	@Test
	public void splitTest(){
		String str = "a,b ,c,d,,e";
		List<String> split = StrUtil.split(str, ',', -1, true, true);
		//测试空是否被去掉
		Assert.assertEquals(5, split.size());
		//测试去掉两边空白符是否生效
		Assert.assertEquals("b", split.get(1));
	}