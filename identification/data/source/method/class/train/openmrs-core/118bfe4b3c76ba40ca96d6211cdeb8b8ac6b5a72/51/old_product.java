@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_PATIENT_COHORTS })
	public Cohort getCohortByUuid(String uuid);