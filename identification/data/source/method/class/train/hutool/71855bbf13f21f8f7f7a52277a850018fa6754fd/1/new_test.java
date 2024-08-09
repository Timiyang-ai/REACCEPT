	@Test
	@Ignore
	public void downloadStringTest() {
		String url = "https://www.baidu.com";
		// 从远程直接读取字符串，需要自定义编码，直接调用JDK方法
		String content2 = HttpUtil.downloadString(url, CharsetUtil.UTF_8);
		Console.log(content2);
	}