@Override
	public PatientProgram savePatientProgram(PatientProgram patientProgram) throws APIException {
		
		if (patientProgram.getPatient() == null || patientProgram.getProgram() == null) {
			throw new APIException("PatientProgram.requires", (Object[]) null);
		}
		
		// Patient State
		for (PatientState state : patientProgram.getStates()) {
			if (state.getState() == null) {
				throw new APIException("PatientState.requires", (Object[]) null);
			}
			if (state.getPatientProgram() == null) {
				state.setPatientProgram(patientProgram);
			} else if (!state.getPatientProgram().equals(patientProgram)) {
				throw new APIException("PatientProgram.already.assigned", new Object[] { state.getPatientProgram() });
			}
			if (patientProgram.getVoided() || state.getVoided()) {
				state.setVoided(true);
				if (state.getVoidReason() == null && patientProgram.getVoidReason() != null) {
					state.setVoidReason(patientProgram.getVoidReason());
				}
			}
		}
		// Makes sure that the end dates of most recent states in each workflow
		// and the program end date are consistent
		if (patientProgram.getDateCompleted() != null) {
			for (PatientState state : patientProgram.getMostRecentStateInEachWorkflow()) {
				// The EndDate of active states only should be updated
				if (state.getEndDate() == null) {
					state.setEndDate(patientProgram.getDateCompleted());
				}
			}
		}

		return dao.savePatientProgram(patientProgram);
	}