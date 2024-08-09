@Transactional(readOnly=true)
	public Patient getPatient(Integer patientId) throws APIException;