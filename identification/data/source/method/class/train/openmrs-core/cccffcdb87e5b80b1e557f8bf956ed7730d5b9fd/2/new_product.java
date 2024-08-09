@Override
	public void globalPropertyChanged(GlobalProperty newValue) {
		allowedLocales = new LinkedHashSet<>();
		for (String allowedLocaleString : newValue.getPropertyValue().split(",")) {
			Locale allowedLocale = LocaleUtility.fromSpecification(allowedLocaleString.trim());
			if (allowedLocale != null) {
				allowedLocales.add(allowedLocale);
			}
		}
	}