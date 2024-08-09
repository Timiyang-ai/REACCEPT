	@Test
	public void getLocale_shouldNotFailIfSessionHasntBeenOpened() {
		Context.closeSession();
		Assert.assertEquals(LocaleUtility.getDefaultLocale(), Context.getLocale());
	}