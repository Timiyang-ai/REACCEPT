public static Locale getDefaultLocale() {
		if (defaultLocaleCache == null) {
			if (Context.isSessionOpen()) {
				try {
					String locale = Context
							.getAdministrationService()
							.getGlobalProperty(
									OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCALE);

					if (StringUtils.hasLength(locale)) {
						try {
							defaultLocaleCache = fromSpecification(locale);
						} catch (Exception t) {
							log.warn(
									"Unable to parse default locale global property value: "
											+ locale, t);
						}
					}
				} catch (Throwable t) {
					// swallow most of the stack trace for most users
					log.warn("Unable to get locale global property value. "
							+ t.getMessage());
					log.trace("Unable to get locale global property value", t);
				}
			}

			// if we weren't able to load the locale from the global property,
			// use the default one
			if (defaultLocaleCache == null)
				defaultLocaleCache = fromSpecification(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCALE_DEFAULT_VALUE);
		}

		return defaultLocaleCache;
	}