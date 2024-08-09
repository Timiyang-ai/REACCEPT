@Override
	public List<ConceptSearchResult> getConcepts(String phrase, Locale locale, boolean includeRetired) throws APIException {
		List<Locale> locales = new Vector<Locale>();
		if (locale != null)
			locales.add(locale);
		
		return getConcepts(phrase, locales, includeRetired, null, null, null, null, null, null, null);
	}