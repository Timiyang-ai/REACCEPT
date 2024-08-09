@Override
	public void validate(Object target, Errors errors) {
		Visit visit = (Visit) target;
		ValidationUtils.rejectIfEmpty(errors, "patient", "Visit.error.patient.required");
		ValidationUtils.rejectIfEmpty(errors, "visitType", "Visit.error.visitType.required");
		ValidationUtils.rejectIfEmpty(errors, "startDatetime", "Visit.error.startDate.required");
		if (visit.getStartDatetime() != null
		        && OpenmrsUtil.compareWithNullAsLatest(visit.getStartDatetime(), visit.getStopDatetime()) > 0) {
			errors.rejectValue("stopDatetime", "Visit.error.endDateBeforeStartDate");
		}
		
		for (VisitAttributeType vat : Context.getVisitService().getAllVisitAttributeTypes()) {
			if (vat.getMinOccurs() > 0 || vat.getMaxOccurs() != null) {
				int numFound = 0;
				for (VisitAttribute attr : visit.getActiveAttributes()) {
					if (attr.getAttributeType().equals(vat))
						++numFound;
				}
				if (vat.getMinOccurs() > 0) {
					if (numFound < vat.getMinOccurs()) {
						// report an error
						if (vat.getMinOccurs() == 1)
							errors.rejectValue("activeAttributes", "error.required", new Object[] { vat.getName() }, null);
						else
							errors.rejectValue("activeAttributes", "attribute.error.minOccurs", new Object[] {
							        vat.getName(), vat.getMinOccurs() }, null);
					}
				}
				if (vat.getMaxOccurs() != null) {
					if (numFound > vat.getMaxOccurs()) {
						errors.rejectValue("activeAttributes", "attribute.error.maxOccurs", new Object[] { vat.getName(),
						        vat.getMaxOccurs() }, null);
					}
				}
			}
		}
		
		//If this is not a new visit, validate based on its existing encounters.
		if (visit.getId() != null) {
			Date startDateTime = visit.getStartDatetime();
			Date stopDateTime = visit.getStopDatetime();
			
			List<Encounter> encounters = Context.getEncounterService().getEncountersByVisit(visit);
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
	}