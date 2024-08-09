public Collection<ConceptSynonym> getSynonyms(Locale locale) {
		String conceptLanguage = locale.getLanguage().substring(0, 2);
		Collection<ConceptSynonym> syns = new Vector<ConceptSynonym>();
		for (ConceptSynonym syn : getSynonyms()) {
			String synLanguage = syn.getLocale().getLanguage().substring(0, 2);
			if (synLanguage == null) synLanguage = Context.getLocale().getLanguage().substring(0, 2);
			if (synLanguage.equals(conceptLanguage))
				syns.add(syn);
		}
		log.debug("returning: " + syns);
		return syns;
	}