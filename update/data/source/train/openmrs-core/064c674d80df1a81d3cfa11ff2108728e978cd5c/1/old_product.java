@Override
	public void handle(Patient patient, User voidingUser, Date voidedDate, String voidReason) {
		//void all the encounters associated with this patient
		EncounterService es = Context.getEncounterService();
		List<Encounter> encounters = es.getEncountersByPatient(patient);
		if (CollectionUtils.isNotEmpty(encounters)) {
			for (Encounter encounter : encounters) {
				if (!encounter.getVoided()) {
					// EncounterServiceImpl.voidEncounter and the requiredDataAdvice will set dateVoided to current date 
					//if it is null, we need to set it now to match the patient's date voided so that the unvoid 
					//handler's logic doesn't fail when comparing dates while unvoiding encounters that were voided 
					//with the patient
					encounter.setDateVoided(patient.getDateVoided());
					es.voidEncounter(encounter, voidReason);
				}
			}
		}

		// if patient is voided, we set the membership containing the patient to be voided
		CohortService cs = Context.getCohortService();
		cs.patientVoided(patient);
	}