@Test
	@Verifies(value = "should return unlocalized name when no localization is added", method = "getName()")
	public void getName_shouldReturnUnlocalizedNameWhenNoLocalizationIsAdded() throws Exception {
		FieldType type = new FieldType();
		String expected = "Test";
		LocalizedString ls = LocalizedString.valueOf(expected);
		type.setLocalizedName(ls);
		Assert.assertEquals(expected, type.getName());
	}