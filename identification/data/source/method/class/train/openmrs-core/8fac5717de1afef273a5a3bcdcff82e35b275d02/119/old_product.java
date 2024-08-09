@Authorized( { OpenmrsConstants.PRIV_DELETE_COHORTS })
	public Cohort voidCohort(Cohort cohort, String reason) throws APIException;