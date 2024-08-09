public ConceptClass saveConceptClass(ConceptClass cc) throws APIException {
		if (cc.getDateCreated() == null)
			cc.setDateCreated(new Date());
		if (cc.getCreator() == null)
			cc.setCreator(Context.getAuthenticatedUser());
		return dao.saveConceptClass(cc);
	}