@Authorized( { PrivilegeConstants.DELETE_COHORTS })
	public Cohort voidCohort(Cohort cohort, String reason) throws APIException;