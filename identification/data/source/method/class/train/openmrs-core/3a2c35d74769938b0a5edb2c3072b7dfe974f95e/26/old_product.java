public void mergePatients(Patient preferred, Patient notPreferred) throws APIException {
		log.debug("Merging patients: (preferred)" + preferred.getPatientId() + ", (notPreferred) " + notPreferred.getPatientId());
		
		// change all encounters
		EncounterService es = context.getEncounterService();
		for (Encounter e : es.getEncounters(notPreferred)){
			e.setPatient(preferred);
			log.debug("Merging encounter " + e.getEncounterId() + " to " + preferred.getPatientId());
			es.updateEncounter(e);
		}
		
		// move all identifiers
		for (PatientIdentifier pi : notPreferred.getIdentifiers()) {
			PatientIdentifier tmpIdentifier = new PatientIdentifier();
			tmpIdentifier.setIdentifier(pi.getIdentifier());
			tmpIdentifier.setIdentifierType(pi.getIdentifierType());
			tmpIdentifier.setLocation(pi.getLocation());
			tmpIdentifier.setPatient(preferred);
			if (!preferred.getIdentifiers().contains(tmpIdentifier)) {
				tmpIdentifier.setCreator(context.getAuthenticatedUser());
				tmpIdentifier.setDateCreated(new Date());
				tmpIdentifier.setVoided(false);
				tmpIdentifier.setVoidedBy(null);
				tmpIdentifier.setVoidReason(null);
				preferred.addIdentifier(tmpIdentifier);
				log.debug("Merging identifier " + tmpIdentifier.getIdentifier() + " to " + preferred.getPatientId());
			}
		}
		
		// move all names
		for (PatientName newName : notPreferred.getNames()) {
			boolean containsName = false;
			for (PatientName currentName : preferred.getNames()) {
				String given = newName.getGivenName();
				String middle = newName.getMiddleName();
				String family = newName.getFamilyName();
				
				if ((given != null && given.equals(currentName.getGivenName())) &&
					(middle != null && middle.equals(currentName.getMiddleName())) &&
					(family != null && family.equals(currentName.getFamilyName()))	
						) {
					containsName = true;
				}
			}
			if (!containsName) {
				PatientName tmpName = PatientName.newInstance(newName);
				tmpName.setPatientNameId(null);
				tmpName.setVoided(false);
				tmpName.setVoidedBy(null);
				tmpName.setVoidReason(null);
				preferred.addName(tmpName);
				log.debug("Merging name " + newName.getGivenName() + " to " + preferred.getPatientId());
			}
		}
		
		// move all addresses
		for (PatientAddress newAddress : notPreferred.getAddresses()) {
			boolean containsAddress = false;
			for (PatientAddress currentAddress : preferred.getAddresses()) {
				String address1 = currentAddress.getAddress1();
				String address2 = currentAddress.getAddress2();
				String cityVillage = currentAddress.getCityVillage();
				
				if ((address1 != null && address1.equals(newAddress.getAddress1())) ||
					(address2 != null && address2.equals(newAddress.getAddress2())) ||
					(cityVillage != null && cityVillage.equals(newAddress.getCityVillage()))	
						) {
					containsAddress = true;
				}
			}
			if (!containsAddress) {
				PatientAddress tmpAddress = (PatientAddress)newAddress.clone();
				tmpAddress.setPatientAddressId(null);
				tmpAddress.setVoided(false);
				tmpAddress.setVoidedBy(null);
				tmpAddress.setVoidReason(null);
				preferred.addAddress(tmpAddress);
				log.debug("Merging address " + newAddress.getPatientAddressId() + " to " + preferred.getPatientId());
			}
		}
		
		// move all other patient info
		
		if (!"M".equals(preferred.getGender()) && !"F".equals(preferred.getGender()))
			preferred.setGender(notPreferred.getGender());
		
		if (preferred.getRace() == null || preferred.getRace().equals(""))
			preferred.setRace(notPreferred.getRace());
		
		if (preferred.getBirthdate() == null || preferred.getBirthdate().equals("") ||
				( preferred.getBirthdateEstimated() && !notPreferred.getBirthdateEstimated())) {
			preferred.setBirthdate(notPreferred.getBirthdate());
			preferred.setBirthdateEstimated(notPreferred.getBirthdateEstimated());
		}
		
		if (preferred.getBirthplace() == null || preferred.getBirthplace().equals(""))
			preferred.setBirthplace(notPreferred.getBirthplace());
		
		if (preferred.getTribe() == null)
			preferred.setTribe(notPreferred.getTribe());
		
		if (preferred.getCitizenship() == null || preferred.getCitizenship().equals(""))
			preferred.setCitizenship(notPreferred.getCitizenship());
		
		if (preferred.getMothersName() == null || preferred.getMothersName().equals(""))
			preferred.setMothersName(notPreferred.getMothersName());
		
		if (preferred.getCivilStatus() == null)
			preferred.setCivilStatus(notPreferred.getCivilStatus());
		
		if (preferred.getDeathDate() == null || preferred.getDeathDate().equals(""))
			preferred.setDeathDate(notPreferred.getDeathDate());
		
		if (preferred.getCauseOfDeath() == null || preferred.getCauseOfDeath().equals(""))
			preferred.setCauseOfDeath(notPreferred.getCauseOfDeath());
		
		if (preferred.getHealthDistrict() == null || preferred.getHealthDistrict().equals(""))
			preferred.setHealthDistrict(notPreferred.getHealthDistrict());
		
		if (preferred.getHealthCenter() == null)
			preferred.setHealthCenter(notPreferred.getHealthCenter());
		
		// void the non preferred patient
		voidPatient(notPreferred, "Merged with patient #" + preferred.getPatientId());
		
		// Save the newly update preferred patient
		// This must be called _after_ voiding the nonPreferred patient so that
		//  a "Duplicate Identifier" error doesn't pop up.
		updatePatient(preferred);
		
	}