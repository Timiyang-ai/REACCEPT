@Authorized( { PrivilegeConstants.VIEW_PATIENT_COHORTS })
	public List<Cohort> getCohortsContainingPatient(Patient patient) throws APIException;