public static Set<Locale> getLocalesInOrder() {
		
		Set<Locale> locales = new LinkedHashSet<Locale>();
		locales.add(Context.getLocale());
		locales.add(getDefaultLocale());
		locales.addAll(Context.getAdministrationService().getAllowedLocales());
		locales.add(Locale.ENGLISH);
		
		return locales;
	}