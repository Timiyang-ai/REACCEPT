@Override
	public void validate(Object target, Errors errors) {
		if (!ValidateUtil.isValidationOn()) {
			return;
		}
		
		Visit visit = (Visit) target;
		ValidationUtils.rejectIfEmpty(errors, "patient", "Visit.error.patient.required");
		ValidationUtils.rejectIfEmpty(errors, "visitType", "Visit.error.visitType.required");
		ValidationUtils.rejectIfEmpty(errors, "startDatetime", "Visit.error.startDate.required");
		if (visit.getStartDatetime() != null
		        && OpenmrsUtil.compareWithNullAsLatest(visit.getStartDatetime(), visit.getStopDatetime()) > 0) {
			errors.rejectValue("stopDatetime", "Visit.error.endDateBeforeStartDate");
		}
		
		//If this is not a new visit, validate based on its existing encounters.
		if (visit.getId() != null) {
			Date startDateTime = visit.getStartDatetime();
			Date stopDateTime = visit.getStopDatetime();
			
			List<Encounter> encounters = Context.getEncounterService().getEncountersByVisit(visit, false);
			for (Encounter encounter : encounters) {
				if (encounter.getEncounterDatetime().before(startDateTime)) {
					errors.rejectValue("startDatetime", "Visit.encountersCannotBeBeforeStartDate",
					    "This visit has encounters whose dates cannot be before the start date of the visit.");
					break;
				} else if (stopDateTime != null && encounter.getEncounterDatetime().after(stopDateTime)) {
					errors.rejectValue("stopDatetime", "Visit.encountersCannotBeAfterStopDate",
					    "This visit has encounters whose dates cannot be after the stop date of the visit.");
					break;
				}
			}
		}
		
		// check attributes
		super.validateAttributes(visit, errors, Context.getVisitService().getAllVisitAttributeTypes());
	}