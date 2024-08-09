@Authorized( { PrivilegeConstants.VIEW_PATIENT_COHORTS })
	public List<Cohort> getAllCohorts(boolean includeVoided) throws APIException;