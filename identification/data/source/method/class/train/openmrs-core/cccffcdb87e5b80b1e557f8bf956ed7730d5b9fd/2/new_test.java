	@Test
	public void globalPropertyChanged_shouldSetAllowedLocalesIfGlobalPropertyIsAnEmptyString() {

		GlobalProperty gp = new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_LOCALE_ALLOWED_LIST, "");

		globalLocaleList.globalPropertyChanged(gp);

		assertThat(globalLocaleList.getAllowedLocales(), contains(Locale.ROOT));
	}