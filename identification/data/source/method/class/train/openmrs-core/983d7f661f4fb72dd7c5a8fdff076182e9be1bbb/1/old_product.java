@Override
	public void purgeConceptMapType(ConceptMapType conceptMapType) throws APIException {
		if (dao.isConceptMapTypeInUse(conceptMapType))
			throw new APIException(Context.getMessageSourceService().getMessage("ConceptMapType.inUse"));
		dao.purgeConceptMapType(conceptMapType);
	}