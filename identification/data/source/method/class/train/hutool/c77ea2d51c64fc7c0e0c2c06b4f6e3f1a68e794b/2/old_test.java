	@Test
	public void decodeParamsTest() {
		String paramsStr = "uuuu=0&a=b&c=%3F%23%40!%24%25%5E%26%3Ddsssss555555";
		Map<String, List<String>> map = HttpUtil.decodeParams(paramsStr, CharsetUtil.UTF_8);
		Assert.assertEquals("0", map.get("uuuu").get(0));
		Assert.assertEquals("b", map.get("a").get(0));
		Assert.assertEquals("?#@!$%^&=dsssss555555", map.get("c").get(0));
	}