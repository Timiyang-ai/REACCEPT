public static Set<Locale> getLocalesInOrder() {

		Set<Locale> locales = new LinkedHashSet<Locale>();
		locales.add(Context.getLocale());
		locales.add(getDefaultLocale());
		if (localesAllowedListCache == null)
			localesAllowedListCache = Context.getAdministrationService()
					.getAllowedLocales();

		if (localesAllowedListCache != null)
			locales.addAll(localesAllowedListCache);

		locales.add(Locale.ENGLISH);

		return locales;
	}