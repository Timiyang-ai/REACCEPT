public void unvoidPatient(Patient patient) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_EDIT_PATIENTS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_EDIT_PATIENTS);
		
		String voidReason = patient.getVoidReason();
		if (voidReason == null)
			voidReason = "";
		
		for (PatientName pn : patient.getNames()) {
			if (voidReason.equals(pn.getVoidReason())) {
				pn.setVoided(false);
				pn.setVoidReason(null);
			}
		}
		for (PatientAddress pa : patient.getAddresses()) {
			if (voidReason.equals(pa.getVoidReason())) {
				pa.setVoided(false);
				pa.setVoidReason(null);
			}
		}
		for (PatientIdentifier pi : patient.getIdentifiers()) {
			if (voidReason.equals(pi.getVoidReason())) {
				pi.setVoided(false);
				pi.setVoidReason(null);
			}
		}
		patient.setVoided(false);
		patient.setVoidedBy(null);
		patient.setDateVoided(null);
		patient.setVoidReason(null);
		updatePatient(patient);
	}