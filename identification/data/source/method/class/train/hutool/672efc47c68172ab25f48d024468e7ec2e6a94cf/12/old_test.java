	@Test
	public void removeHtmlTagTest() {
		//非闭合标签
		String str = "pre<img src=\"xxx/dfdsfds/test.jpg\">";
		String result = HtmlUtil.removeHtmlTag(str, "img");
		Assert.assertEquals("pre", result);
		
		//闭合标签
		str = "pre<img>";
		result = HtmlUtil.removeHtmlTag(str, "img");
		Assert.assertEquals("pre", result);
		
		//闭合标签
		str = "pre<img src=\"xxx/dfdsfds/test.jpg\" />";
		result = HtmlUtil.removeHtmlTag(str, "img");
		Assert.assertEquals("pre", result);
		
		//闭合标签
		str = "pre<img />";
		result = HtmlUtil.removeHtmlTag(str, "img");
		Assert.assertEquals("pre", result);
		
		//包含内容标签
		str = "pre<div class=\"test_div\">dfdsfdsfdsf</div>";
		result = HtmlUtil.removeHtmlTag(str, "div");
		Assert.assertEquals("pre", result);
		
		//带换行
		str = "pre<div class=\"test_div\">\r\n\t\tdfdsfdsfdsf\r\n</div>";
		result = HtmlUtil.removeHtmlTag(str, "div");
		Assert.assertEquals("pre", result);
	}