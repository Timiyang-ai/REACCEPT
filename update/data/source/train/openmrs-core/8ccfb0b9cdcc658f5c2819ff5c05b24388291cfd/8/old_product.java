public Cohort voidCohort(Cohort cohort, String reason) {
		// other setters done by the save handlers
		return Context.getCohortService().saveCohort(cohort);
	}