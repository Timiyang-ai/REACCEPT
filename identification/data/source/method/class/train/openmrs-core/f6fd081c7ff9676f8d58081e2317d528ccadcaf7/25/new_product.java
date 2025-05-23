@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.GET_FORMS)
	public List<Form> getForms(String partialNameSearch, Boolean published, Collection<EncounterType> encounterTypes,
	        Boolean retired, Collection<FormField> containingAnyFormField, Collection<FormField> containingAllFormFields,
	        Collection<Field> fields);