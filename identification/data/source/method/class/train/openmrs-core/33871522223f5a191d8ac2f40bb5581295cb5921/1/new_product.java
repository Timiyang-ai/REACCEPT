@Override
	@Transactional(readOnly = true)
	public List<Concept> getDrugRoutes() {
		String conceptUuid = Context.getAdministrationService().getGlobalProperty(OpenmrsConstants.DRUG_ROUTE_CONCEPT_UUID);
		Concept concept = Context.getConceptService().getConceptByUuid(conceptUuid);
		if (concept != null && concept.isSet()) {
			return concept.getSetMembers();
		}
		return Collections.EMPTY_LIST;
	}