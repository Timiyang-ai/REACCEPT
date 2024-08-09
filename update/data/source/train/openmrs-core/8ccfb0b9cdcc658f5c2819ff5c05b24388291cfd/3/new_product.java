@Override
	public void patientVoided(Patient patient) throws APIException {
		List<Cohort> cohorts = Context.getCohortService().getCohortsContainingPatient(patient);
		for (Cohort cohort : cohorts) {
			if (patient.getVoided()) {
				List<CohortMembership> memberships = cohort.getMemberships(false);
				memberships.forEach(m -> {
					m.setVoided(patient.getVoided());
					m.setDateVoided(patient.getDateVoided());
					m.setVoidedBy(patient.getVoidedBy());
					m.setVoidReason(patient.getVoidReason());
				});
			}
		}
	}