@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PATIENT_COHORTS })
	public Cohort getCohortByUuid(String uuid);