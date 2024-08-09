	@Test
	public void colorToHexCode()
	{
		COLOR_HEXSTRING_MAP.forEach((color, hex) ->
		{
			assertEquals(hex, ColorUtil.colorToHexCode(color));
		});
	}