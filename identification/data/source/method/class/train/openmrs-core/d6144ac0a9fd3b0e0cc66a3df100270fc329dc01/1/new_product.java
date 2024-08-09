public Map<Locale, String> getValueAsString() {
		Map<Locale, String> ret = new HashMap<Locale, String>();
		for (Locale locale : OpenmrsConstants.OPENMRS_CONCEPT_LOCALES()) {
			ret.put(locale, getValueAsString(locale));
		}
		return ret;
	}