@Override
	public void globalPropertyChanged(GlobalProperty newValue) {
		allowedLocales = new LinkedHashSet<Locale>();
		for (String allowedLocaleString : newValue.getPropertyValue().split(",")) {
			try {
				Locale allowedLocale = LocaleUtility.fromSpecification(allowedLocaleString.trim());
				if (allowedLocale != null) {
					allowedLocales.add(allowedLocale);
				}
			}
			catch (Exception e) {
				// bad locale spec? just ignore it. the UI should take care of
				// guiding the user.
			}
		}
	}