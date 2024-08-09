public Collection<ConceptSynonym> getSynonyms(Locale locale) {
		String loc = locale.getLanguage().substring(0, 2);
		Collection<ConceptSynonym> syns = new Vector<ConceptSynonym>();
		for (ConceptSynonym syn : getSynonyms()) {
			String lang = syn.getLocale();
			if (lang == null) lang = "en";
			if (lang.equals(loc))
				syns.add(syn);
		}
		log.debug("returning: " + syns);
		return syns;
	}