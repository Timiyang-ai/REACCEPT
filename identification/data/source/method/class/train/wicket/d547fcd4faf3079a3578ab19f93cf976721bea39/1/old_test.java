@Test
	public void getLocaleFromFilename()
	{
		ResourceUtils.PathLocale pathLocale;

		pathLocale = ResourceUtils.getLocaleFromFilename("some.ext");
		assertThat(pathLocale.path, is(equalTo("some.ext")));
		assertThat(pathLocale.locale, is(nullValue()));

		pathLocale = ResourceUtils.getLocaleFromFilename("some.min.ext");
		assertThat(pathLocale.path, is(equalTo("some.min.ext")));
		assertThat(pathLocale.locale, is(nullValue()));

		pathLocale = ResourceUtils.getLocaleFromFilename("some.min_en.ext");
		assertThat(pathLocale.path, is(equalTo("some.min.ext")));
		assertThat(pathLocale.locale, is(Locale.ENGLISH));

		pathLocale = ResourceUtils.getLocaleFromFilename("some.min_fr_CA.ext");
		assertThat(pathLocale.path, is(equalTo("some.min.ext")));
		assertThat(pathLocale.locale, is(Locale.CANADA_FRENCH));

		String localeVariant = "blah";
		pathLocale = ResourceUtils.getLocaleFromFilename("some.min_fr_CA_"+localeVariant+".ext");
		assertThat(pathLocale.path, is(equalTo("some.min.ext")));
		assertThat(pathLocale.locale.getLanguage(), is("fr"));
		assertThat(pathLocale.locale.getCountry(), is("CA"));
		assertThat(pathLocale.locale.getVariant(), is(localeVariant));
	}