@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_FORMS)
	public List<Form> getForms(String partialNameSearch, Boolean published, Collection<EncounterType> encounterTypes,
	                           Boolean retired, Collection<FormField> containingAnyFormField,
	                           Collection<FormField> containingAllFormFields);