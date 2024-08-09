public List<Drug> getDrugs(String phrase) {
		List<Drug> drugs = new ArrayList<Drug>();
		// trying to treat search phrase as drug id
		try {
			Integer drugId = Integer.parseInt(phrase);
			Drug targetDrug = getDrug(drugId);
			// if drug was found add it to result
			if (targetDrug != null) {
				drugs.add(targetDrug);
			}
		}
		catch (NumberFormatException e) {
			// do nothing
		}
		
		// also try to treat search phrase as drug concept id
		try {
			Integer conceptId = Integer.parseInt(phrase);
			Concept targetConcept = Context.getConceptService().getConcept(conceptId);
			if (targetConcept != null) {
				drugs.addAll(Context.getConceptService().getDrugsByConcept(targetConcept));
			}
		}
		catch (NumberFormatException e) {
			// do nothing
		}
		
		drugs.addAll(dao.getDrugs(phrase));
		return drugs;
	}