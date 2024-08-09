@Authorized( { OpenmrsConstants.PRIV_EDIT_COHORTS })
	public Cohort removePatientFromCohort(Cohort cohort, Patient patient) throws APIException;