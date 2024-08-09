	@Test
	public void parseBorder() {
		String border = "dashed";
		Map<String, String> map = css.parseBorder(border);
		Assert.assertTrue(map.containsKey("border-left-style"));
		Assert.assertEquals("dashed", map.get("border-left-style"));
		Assert.assertTrue(map.containsKey("border-top-style"));
		Assert.assertEquals("dashed", map.get("border-top-style"));
		Assert.assertTrue(map.containsKey("border-bottom-style"));
		Assert.assertEquals("dashed", map.get("border-bottom-style"));
		Assert.assertTrue(map.containsKey("border-right-style"));
		Assert.assertEquals("dashed", map.get("border-right-style"));
	}