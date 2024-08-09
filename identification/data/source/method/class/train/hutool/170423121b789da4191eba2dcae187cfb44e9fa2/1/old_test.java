	@Test
	public void delAllTest() {
		// 删除所有匹配到的内容
		String content = "发东方大厦eee![images]http://abc.com/2.gpg]好机会eee![images]http://abc.com/2.gpg]好机会";
		String resultDelAll = ReUtil.delAll("!\\[images\\][^\\u4e00-\\u9fa5\\\\s]*", content);
		Assert.assertEquals("发东方大厦eee好机会eee好机会", resultDelAll);
	}