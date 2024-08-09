public Cohort saveCohort(Cohort cohort) throws APIException {
		if (cohort.getCohortId() == null) {
			Context.requirePrivilege(PrivilegeConstants.ADD_COHORTS);
		} else {
			Context.requirePrivilege(PrivilegeConstants.EDIT_COHORTS);
		}
		if (cohort.getName() == null) {
			throw new APIException("Cohort.save.nameRequired", (Object[]) null);
		}
		if (cohort.getDescription() == null) {
			throw new APIException("Cohort.save.descriptionRequired", (Object[]) null);
		}
		if (log.isInfoEnabled()) {
			log.info("Saving cohort " + cohort);
		}

		return dao.saveCohort(cohort);
	}