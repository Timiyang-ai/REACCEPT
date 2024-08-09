	@Test
	public void cutTest(){
		String str = "aaabbbcccdddaadfdfsdfsdf0";
		String[] cut = StrUtil.cut(str, 4);
		Assert.assertArrayEquals(new String[] {"aaab", "bbcc", "cddd", "aadf", "dfsd", "fsdf", "0"}, cut);
	}