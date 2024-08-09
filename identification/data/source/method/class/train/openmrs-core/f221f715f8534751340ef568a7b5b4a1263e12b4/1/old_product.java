@Override
	public List<Locale> getSearchLocales(User user) throws APIException {
		Set<Locale> locales = new LinkedHashSet<Locale>();
		
		Locale currentLocale = Context.getLocale();
		
		locales.add(currentLocale); //the currently used full locale
		
		//the currently used language
		locales.add(new Locale(currentLocale.getLanguage()));
		
		if (user != null) {
			List<Locale> proficientLocales = user.getProficientLocales();
			if (proficientLocales != null) {
				//limit proficient locales to only allowed locales
				List<Locale> allowedLocales = getAllowedLocales();
				if (allowedLocales != null) {
					Set<Locale> retainLocales = new HashSet<Locale>();
					
					for (Locale allowedLocale : allowedLocales) {
						retainLocales.add(allowedLocale);
						retainLocales.add(new Locale(allowedLocale.getLanguage()));
					}
					
					proficientLocales.retainAll(retainLocales);
				}
				
				locales.addAll(proficientLocales); //allowed user proficient locales
			}
		}
		
		return new ArrayList<Locale>(locales);
	}