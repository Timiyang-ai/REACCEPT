@Deprecated
	@Authorized({ PrivilegeConstants.GET_PATIENT_COHORTS })
	public Cohort getCohort(String name) throws APIException;