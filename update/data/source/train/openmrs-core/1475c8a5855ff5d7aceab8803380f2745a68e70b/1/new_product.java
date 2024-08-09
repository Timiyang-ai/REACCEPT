public void voidLastState(ProgramWorkflow workflow, User voidBy, Date voidDate, String voidReason) {
		List<PatientState> states = statesInWorkflow(workflow, false);
		if (voidDate == null) {
			voidDate = new Date();
		}
		PatientState last = null;
		PatientState nextToLast = null;
		if (!states.isEmpty()) {
			last = states.get(states.size() - 1);
		}
		if (states.size() > 1) {
			nextToLast = states.get(states.size() - 2);
		}
		if (last != null) {
			last.setVoided(true);
			last.setVoidedBy(voidBy);
			last.setDateVoided(voidDate);
			last.setVoidReason(voidReason);
		}
		if (nextToLast != null && nextToLast.getEndDate() != null) {
			nextToLast.setEndDate(nextToLast.getPatientProgram() != null
			        && nextToLast.getPatientProgram().getDateCompleted() != null ? nextToLast.getPatientProgram()
			        .getDateCompleted() : null);
			nextToLast.setDateChanged(voidDate);
			nextToLast.setChangedBy(voidBy);
		}
	}