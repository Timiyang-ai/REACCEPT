public void validate(Object obj, Errors errors) {
		if (log.isDebugEnabled())
			log.debug(this.getClass().getName() + ".validate...");
		
		if (obj == null)
			throw new IllegalArgumentException("The parameter obj should not be null");
		
		PatientProgram program = (PatientProgram) obj;
		ValidationUtils.rejectIfEmpty(errors, "patient", "error.required", Context.getMessageSourceService().getMessage(
		    "general.patient"));
		
		Set<PatientState> patientStates = program.getStates();
		if (patientStates != null) {
			//map to keep track of valid unique state and start date combinations
			Map<ProgramWorkflowState, Date> stateStartDateMap = null;
			//check for duplicate states
			for (PatientState patientState : patientStates) {
				if (patientState.isVoided())
					continue;
				
				if (patientState.getStartDate() != null) {
					if (OpenmrsUtil.compareWithNullAsLatest(patientState.getEndDate(), patientState.getStartDate()) < 0) {
						errors.reject("PatientState.error.endDateCannotBeBeforeEndDate");
						return;
					}
				}
				
				if (stateStartDateMap == null)
					stateStartDateMap = new HashMap<ProgramWorkflowState, Date>();
				
				//check if we already have a patient state with the same work flow state and start date
				//note that we can have null start dates, meaning we won't allow multiple patient states with
				//the same work flow states and null start dates
				if (stateStartDateMap.containsKey(patientState.getState())
				        && OpenmrsUtil.nullSafeEquals(stateStartDateMap.get(patientState.getState()), patientState
				                .getStartDate())) {
					errors.reject("PatientState.error.duplicatePatientStates");
					return;
				}
				
				stateStartDateMap.put(patientState.getState(), patientState.getStartDate());
			}
		}
		
		//TODO validate other fields
	}