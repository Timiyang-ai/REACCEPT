@Override
	public void validate(Object target, Errors errors) {
		
		if (target == null) {
			throw new IllegalArgumentException("Allergy should not be null");
		}
		
		ValidationUtils.rejectIfEmpty(errors, "patient", "allergyapi.patient.required");
		
		Allergy allergy = (Allergy) target;
		
		if (allergy.getAllergen() == null) {
			errors.rejectValue("allergen", "allergyapi.allergen.required");
		} else {
			Allergen allergen = allergy.getAllergen();
			if (allergen.getAllergenType() == null) {
				errors.rejectValue("allergen", "allergyapi.allergenType.required");
			}
			
			if (allergen.getCodedAllergen() == null && StringUtils.isBlank(allergen.getNonCodedAllergen())) {
				errors.rejectValue("allergen", "allergyapi.allergen.codedOrNonCodedAllergen.required");
			} else if (!allergen.isCoded() && StringUtils.isBlank(allergen.getNonCodedAllergen())) {
				errors.rejectValue("allergen", "allergyapi.allergen.nonCodedAllergen.required");
			}
			
			if (allergy.getAllergyId() == null && allergy.getPatient() != null) {
				Allergies existingAllergies = patientService.getAllergies(allergy.getPatient());
				if (existingAllergies.containsAllergen(allergy)) {
					String name;
					if (allergen.isCoded()) {
						String key = "ui.i18n.Concept.name." + allergen.getCodedAllergen().getUuid();
						name = Context.getMessageSourceService().getMessage(key);
						if (key.equals(name)) {
							name = allergen.toString();
						}
					}
					else {
						name = allergen.getNonCodedAllergen();
					}
						
					errors.rejectValue("allergen", "allergyapi.message.duplicateAllergen", new Object[] { name }, null);
				}
			}
		}
	}