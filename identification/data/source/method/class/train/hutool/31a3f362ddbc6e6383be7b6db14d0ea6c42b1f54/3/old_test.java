	@Test
	@Ignore
	public void getTest() {
		String result1 = HttpUtil.get("http://photo.qzone.qq.com/fcgi-bin/fcg_list_album?uin=88888&outstyle=2", "GBK");
		Console.log(result1);
	}