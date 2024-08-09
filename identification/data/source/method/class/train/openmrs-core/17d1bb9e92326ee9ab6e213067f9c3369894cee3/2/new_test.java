	@Test
	public void getLocalesInOrder_shouldAlwaysHaveEnglishIncludedInTheReturnedCollection() {
		Set<Locale> localesInOrder = LocaleUtility.getLocalesInOrder();
		Assert.assertEquals(true, localesInOrder.contains(Locale.ENGLISH));
	}