@Authorized( { OpenmrsConstants.PRIV_VIEW_PATIENT_IDENTIFIERS })
	public void checkPatientIdentifiers(Patient patient)
	        throws PatientIdentifierException;