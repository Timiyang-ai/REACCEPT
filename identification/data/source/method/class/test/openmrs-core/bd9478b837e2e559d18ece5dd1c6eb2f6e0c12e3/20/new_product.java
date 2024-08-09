public void unvoidPatient(Patient patient) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_EDIT_PATIENTS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_EDIT_PATIENTS);
		getPatientDAO().unvoidPatient(patient);
	}