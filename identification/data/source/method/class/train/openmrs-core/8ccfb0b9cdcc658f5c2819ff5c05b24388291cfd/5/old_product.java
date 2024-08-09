@Authorized( {PrivilegeConstants.EDIT_COHORTS })
	void patientUnvoided(Patient patient, User voidedBy, Date dateVoided, String voidReason);