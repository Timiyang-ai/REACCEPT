public void voidPatient(Patient patient, String reason) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_EDIT_PATIENTS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_EDIT_PATIENTS);
		
		for (PatientName pn : patient.getNames()) {
			if (!pn.isVoided()) {
				pn.setVoided(true);
				pn.setVoidReason(reason);
			}
		}
		for (PatientAddress pa : patient.getAddresses()) {
			if (!pa.isVoided()) {
				pa.setVoided(true);
				pa.setVoidReason(reason);
			}
		}
		for (PatientIdentifier pi : patient.getIdentifiers()) {
			if (!pi.isVoided()) {
				pi.setVoided(true);
				pi.setVoidReason(reason);
			}
		}
		
		patient.setVoided(true);
		patient.setVoidedBy(context.getAuthenticatedUser());
		patient.setDateVoided(new Date());
		patient.setVoidReason(reason);
		updatePatient(patient);
	}