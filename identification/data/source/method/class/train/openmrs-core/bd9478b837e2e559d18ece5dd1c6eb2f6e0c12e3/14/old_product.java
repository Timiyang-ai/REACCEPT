public Patient getPatient(Integer patientId) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_VIEW_PATIENTS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_VIEW_PATIENTS);
		return getPatientDAO().getPatient(patientId);
	}