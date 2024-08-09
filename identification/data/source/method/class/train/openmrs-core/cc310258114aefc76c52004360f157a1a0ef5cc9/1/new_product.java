@Authorized({ PrivilegeConstants.EDIT_COHORTS })
	void notifyPatientUnvoided(Patient patient, User originallyVoidedBy, Date originalDateVoided);