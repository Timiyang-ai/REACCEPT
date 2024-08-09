@Authorized( { PrivilegeConstants.GET_PATIENT_COHORTS })
	public List<Cohort> getCohortsContainingPatient(Patient patient, boolean includeVoided, Date asOfDate);