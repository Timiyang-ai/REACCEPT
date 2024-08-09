public void validate(Object obj, Errors errors) {
		if (log.isDebugEnabled())
			log.debug(this.getClass().getName() + ".validate...");
		
		if (obj == null)
			throw new IllegalArgumentException("The parameter obj should not be null");
		MessageSourceService mss = Context.getMessageSourceService();
		PatientProgram patientProgram = (PatientProgram) obj;
		ValidationUtils.rejectIfEmpty(errors, "patient", "error.required",
		    new Object[] { mss.getMessage("general.patient") });
		ValidationUtils.rejectIfEmpty(errors, "program", "error.required",
		    new Object[] { mss.getMessage("Program.program") });
		
		if (errors.hasErrors())
			return;
		
		Set<ProgramWorkflow> workFlows = patientProgram.getProgram().getWorkflows();
		//Patient state validation is specific to a work flow
		for (ProgramWorkflow workFlow : workFlows) {
			Set<PatientState> patientStates = patientProgram.getStates();
			if (patientStates != null) {
				//Set to store to keep track of unique valid state and start date combinations
				Set<String> statesAndStartDates = new HashSet<String>();
				PatientState latestState = null;
				boolean foundCurrentPatientState = false;
				boolean foundStateWithNullStartDate = false;
				for (PatientState patientState : patientStates) {
					if (patientState.isVoided())
						continue;
					
					String missingRequiredFieldCode = null;
					//only the initial state can have a null start date
					if (patientState.getStartDate() == null) {
						if (foundStateWithNullStartDate)
							missingRequiredFieldCode = "general.dateStart";
						else
							foundStateWithNullStartDate = true;
					} else if (patientState.getState() == null) {
						missingRequiredFieldCode = "State.state";
					}
					
					if (missingRequiredFieldCode != null) {
						errors.rejectValue("states", "PatientState.error.requiredField", new Object[] { mss
						        .getMessage(missingRequiredFieldCode) }, null);
						return;
					}
					
					//state should belong to one of the workflows in the program
					boolean isValidPatientState = false;
					for (ProgramWorkflow wf : patientProgram.getProgram().getAllWorkflows()) {
						if (wf.getStates().contains(patientState.getState())) {
							isValidPatientState = true;
							break;
						}
					}
					
					if (!isValidPatientState) {
						errors.rejectValue("states", "PatientState.error.invalidPatientState",
						    new Object[] { patientState }, null);
						return;
					}
					
					//will validate it with other states in its workflow
					if (!patientState.getState().getProgramWorkflow().equals(workFlow))
						continue;
					
					if (OpenmrsUtil.compareWithNullAsLatest(patientState.getEndDate(), patientState.getStartDate()) < 0) {
						errors.rejectValue("states", "PatientState.error.endDateCannotBeBeforeStartDate");
						return;
					} else if (statesAndStartDates.contains(patientState.getState().getId() + ""
					        + patientState.getStartDate())) {
						// we already have a patient state with the same work flow state and start date
						errors.rejectValue("states", "PatientState.error.duplicatePatientStates");
						return;
					}
					
					//Ensure that the patient is only in one state at a given time
					if (!foundCurrentPatientState && patientState.getEndDate() == null)
						foundCurrentPatientState = true;
					else if (foundCurrentPatientState && patientState.getEndDate() == null) {
						errors.rejectValue("states", "PatientProgram.error.cannotBeInMultipleStates");
						return;
					}
					
					if (latestState == null)
						latestState = patientState;
					else {
						if (patientState.compareTo(latestState) > 0) {
							//patient should have already left this state since it is older
							if (latestState.getEndDate() == null) {
								errors.rejectValue("states", "PatientProgram.error.cannotBeInMultipleStates");
								return;
							} else if (OpenmrsUtil.compareWithNullAsEarliest(patientState.getStartDate(), latestState
							        .getEndDate()) < 0) {
								//current state was started before a previous state was ended
								errors.rejectValue("states", "PatientProgram.error.foundOverlappingStates", new Object[] {
								        patientState.getStartDate(), latestState.getEndDate() }, null);
								return;
							}
							latestState = patientState;
						} else if (patientState.compareTo(latestState) < 0) {
							//patient should have already left this state since it is older
							if (patientState.getEndDate() == null) {
								errors.rejectValue("states", "PatientProgram.error.cannotBeInMultipleStates");
								return;
							} else if (OpenmrsUtil.compareWithNullAsEarliest(latestState.getStartDate(), patientState
							        .getEndDate()) < 0) {
								//latest state was started before a previous state was ended
								errors.rejectValue("states", "PatientProgram.error.foundOverlappingStates");
								return;
							}
						}
					}
					
					statesAndStartDates.add(patientState.getState().getId() + "" + patientState.getStartDate());
				}
			}
		}
		//
	}