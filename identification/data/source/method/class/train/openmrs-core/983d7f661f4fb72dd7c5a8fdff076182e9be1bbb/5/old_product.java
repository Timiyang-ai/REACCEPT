@Override
	public void purgeConceptReferenceTerm(ConceptReferenceTerm conceptReferenceTerm) throws APIException {
		if (dao.isConceptReferenceTermInUse(conceptReferenceTerm))
			throw new APIException(Context.getMessageSourceService().getMessage("ConceptRefereceTerm.inUse"));
		dao.purgeConceptReferenceTerm(conceptReferenceTerm);
	}