	@Test
	public void formatTest() {
		String template = "你好，我是{name}，我的电话是：{phone}";
		String result = StrUtil.format(template, Dict.create().set("name", "张三").set("phone", "13888881111"));
		Assert.assertEquals("你好，我是张三，我的电话是：13888881111", result);
	}