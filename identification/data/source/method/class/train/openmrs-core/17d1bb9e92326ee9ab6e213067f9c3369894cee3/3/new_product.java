public static Set<Locale> getLocalesInOrder() {
		
		Set<Locale> locales = new LinkedHashSet<>();
		locales.add(Context.getLocale());
		locales.add(getDefaultLocale());
		if (localesAllowedListCache == null) {
			localesAllowedListCache = Context.getAdministrationService().getAllowedLocales();
		}
		
		if (localesAllowedListCache != null) {
			locales.addAll(localesAllowedListCache);
		}
		
		locales.add(Locale.ENGLISH);
		locales.add(fromSpecification(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCALE_DEFAULT_VALUE));
		
		return locales;
	}