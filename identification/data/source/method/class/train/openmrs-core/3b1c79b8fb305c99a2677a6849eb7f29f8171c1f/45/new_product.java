@Transactional(readOnly = true)
	public List<Patient> getDuplicatePatientsByAttributes(List<String> attributes) throws APIException {
		
		if (attributes == null || attributes.size() < 1) {
			throw new APIException("There must be at least one attribute supplied to search on");
		}
		
		return dao.getDuplicatePatientsByAttributes(attributes);
	}