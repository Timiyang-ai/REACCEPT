	@Test
	public void toParamsTest() {
		String paramsStr = "uuuu=0&a=b&c=3Ddsssss555555";
		Map<String, List<String>> map = HttpUtil.decodeParams(paramsStr, CharsetUtil.UTF_8);

		String encodedParams = HttpUtil.toParams((Map<String, List<String>>) map);
		Assert.assertEquals(paramsStr, encodedParams);
	}