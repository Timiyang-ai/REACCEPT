@Authorized({ PrivilegeConstants.EDIT_COHORTS })
	public Cohort removePatientFromCohort(Cohort cohort, Patient patient) throws APIException;