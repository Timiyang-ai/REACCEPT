public Collection<ConceptSynonym> getSynonyms(Locale locale) {
		String loc = locale.getLanguage();
		Collection<ConceptSynonym> syns = new Vector<ConceptSynonym>();
		for (ConceptSynonym syn : getSynonyms()) {
			String lang = syn.getLocale();
			if (lang == null) lang = "en"; //TODO temporary hack until db update
			if (lang.equals(loc))
				syns.add(syn);
		}
		log.debug("returning: " + syns);
		return syns;
	}