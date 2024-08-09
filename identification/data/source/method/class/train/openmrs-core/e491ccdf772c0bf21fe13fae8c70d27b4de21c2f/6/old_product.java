public static Locale getDefaultLocale() {
		try {
			String locale = Context.getAdministrationService().getGlobalProperty(
			    OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCALE);
			
			if (StringUtils.hasLength(locale)) {
				try {
					return new Locale(locale);
				}
				catch (Exception t) {
					log.warn("Unable to parse default locale global property value: " + locale, t);
				}
			}
		}
		catch (Throwable t) {
			log.warn("Unable to get locale global property value", t);
		}
		
		return new Locale(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCALE_DEFAULT_VALUE);
	}