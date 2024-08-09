public void patientVoided(Patient patient) throws APIException {
		List<Cohort> cohorts = Context.getCohortService().getCohortsContainingPatient(patient);
		for (Cohort cohort : cohorts) {
			List<CohortMembership> membersToVoid = cohort.getMembers().stream()
					.filter(m -> !m.getVoided() && patient.getVoided() && m.getPatient().getPatientId().equals(patient.getPatientId()))
					.collect(Collectors.toList());
			for (CohortMembership member : membersToVoid) {
				member.setVoided(patient.getVoided());
				member.setDateVoided(patient.getDateVoided());
				member.setVoidedBy(patient.getVoidedBy());
				member.setVoidReason(patient.getVoidReason());
			}
			cohort.removeMember(patient);
			Context.getCohortService().saveCohort(cohort);
		}
	}