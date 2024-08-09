@Override
	public Patient savePatient(Patient patient) throws APIException {
		if (patient.getPatientId() == null) {
			Context.requirePrivilege(PrivilegeConstants.ADD_PATIENTS);
		} else {
			Context.requirePrivilege(PrivilegeConstants.EDIT_PATIENTS);
		}
		if (patient.getVoided()) {
			Context.requirePrivilege(PrivilegeConstants.DELETE_PATIENTS);
		}
		
		if (!patient.getVoided() && patient.getIdentifiers().size() == 1) {
			patient.getPatientIdentifier().setPreferred(true);
		}
		
		if (!patient.getVoided()) {
			checkPatientIdentifiers(patient);
		}
		
		PatientIdentifier preferredIdentifier = null;
		PatientIdentifier possiblePreferredId = patient.getPatientIdentifier();
		if (possiblePreferredId != null && possiblePreferredId.getPreferred() && !possiblePreferredId.getVoided()) {
			preferredIdentifier = possiblePreferredId;
		}
		
		for (PatientIdentifier id : patient.getIdentifiers()) {
			if (preferredIdentifier == null && !id.getVoided()) {
				id.setPreferred(true);
				preferredIdentifier = id;
				continue;
			}
			
			if (!id.equals(preferredIdentifier)) {
				id.setPreferred(false);
			}
		}
		
		PersonName preferredName = null;
		PersonName possiblePreferredName = patient.getPersonName();
		if (possiblePreferredName != null && possiblePreferredName.getPreferred() && !possiblePreferredName.getVoided()) {
			preferredName = possiblePreferredName;
		}
		
		for (PersonName name : patient.getNames()) {
			if (preferredName == null && !name.getVoided()) {
				name.setPreferred(true);
				preferredName = name;
				continue;
			}
			
			if (!name.equals(preferredName)) {
				name.setPreferred(false);
			}
		}
		
		PersonAddress preferredAddress = null;
		PersonAddress possiblePreferredAddress = patient.getPersonAddress();
		if (possiblePreferredAddress != null && possiblePreferredAddress.getPreferred()
		        && !possiblePreferredAddress.getVoided()) {
			preferredAddress = possiblePreferredAddress;
		}
		
		for (PersonAddress address : patient.getAddresses()) {
			if (preferredAddress == null && !address.getVoided()) {
				address.setPreferred(true);
				preferredAddress = address;
				continue;
			}
			
			if (!address.equals(preferredAddress)) {
				address.setPreferred(false);
			}
		}
		
		return dao.savePatient(patient);
	}