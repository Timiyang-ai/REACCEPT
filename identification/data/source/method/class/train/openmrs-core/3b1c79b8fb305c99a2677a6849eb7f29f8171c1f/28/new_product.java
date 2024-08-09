@Transactional(readOnly = true)
	public List<Patient> getDuplicatePatientsByAttributes(List<String> attributes) throws APIException {
		
		if (attributes == null || attributes.size() < 1) {
			throw new APIException("Patient.no.attribute", (Object[]) null);
		}
		
		return dao.getDuplicatePatientsByAttributes(attributes);
	}