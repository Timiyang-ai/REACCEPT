public void mergePatients(Patient preferred, Patient notPreferred) throws APIException, SerializationException {
		log.debug("Merging patients: (preferred)" + preferred.getPatientId() + ", (notPreferred) "
		        + notPreferred.getPatientId());
		if (preferred.getPatientId().equals(notPreferred.getPatientId())) {
			log.debug("Merge operation cancelled: Cannot merge user" + preferred.getPatientId() + " to self");
			throw new APIException("Merge operation cancelled: Cannot merge user " + preferred.getPatientId() + " to self");
		}
		
		PersonMergeLogData mergedData = new PersonMergeLogData();
		mergeVisits(preferred, notPreferred, mergedData);
		mergeEncounters(preferred, notPreferred, mergedData);
		mergeProgramEnrolments(preferred, notPreferred, mergedData);
		mergeRelationships(preferred, notPreferred, mergedData);
		mergeObservationsNotContainedInEncounters(preferred, notPreferred, mergedData);
		mergeIdentifiers(preferred, notPreferred, mergedData);
		
		mergeNames(preferred, notPreferred, mergedData);
		mergeAddresses(preferred, notPreferred, mergedData);
		mergePersonAttributes(preferred, notPreferred, mergedData);
		mergeGenderInformation(preferred, notPreferred, mergedData);
		mergeDateOfBirth(preferred, notPreferred, mergedData);
		mergeDateOfDeath(preferred, notPreferred, mergedData);
		
		// void the non preferred patient
		Context.getPatientService().voidPatient(notPreferred, "Merged with patient #" + preferred.getPatientId());
		
		// void the person associated with not preferred patient
		Context.getPersonService().voidPerson(notPreferred,
		    "The patient corresponding to this person has been voided and Merged with patient #" + preferred.getPatientId());
		
		// associate the Users associated with the not preferred person, to the preferred person.
		changeUserAssociations(preferred, notPreferred, mergedData);
		
		// Save the newly update preferred patient
		// This must be called _after_ voiding the nonPreferred patient so that
		//  a "Duplicate Identifier" error doesn't pop up.
		savePatient(preferred);
		
		//save the person merge log
		PersonMergeLog personMergeLog = new PersonMergeLog();
		personMergeLog.setWinner(preferred);
		personMergeLog.setLoser(notPreferred);
		personMergeLog.setPersonMergeLogData(mergedData);
		Context.getPersonService().savePersonMergeLog(personMergeLog);
	}