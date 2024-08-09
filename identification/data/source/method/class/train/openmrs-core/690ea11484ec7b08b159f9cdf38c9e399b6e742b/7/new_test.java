	@Test
	public void getDateFormat_shouldReturnAPatternWithFourYCharactersInIt() {
		Assert.assertEquals("MM/dd/yyyy", OpenmrsUtil.getDateFormat(Locale.US).toLocalizedPattern());
		Assert.assertEquals("dd/MM/yyyy", OpenmrsUtil.getDateFormat(Locale.UK).toLocalizedPattern());
		Assert.assertEquals("tt.MM.uuuu", OpenmrsUtil.getDateFormat(Locale.GERMAN).toLocalizedPattern());
		Assert.assertEquals("dd-MM-yyyy", OpenmrsUtil.getDateFormat(new Locale("pt", "pt")).toLocalizedPattern());
	}