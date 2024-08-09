@Test
	public void validate_shouldRejectADuplicateAllergen() throws Exception {
		PowerMockito.mockStatic(Context.class);
		MessageSourceService ms = mock(MessageSourceService.class);
		when(Context.getMessageSourceService()).thenReturn(ms);
		
		Allergies allergies = new Allergies();
		Concept aspirin = createMockConcept(null);
		Allergen allergen1 = new Allergen(AllergenType.DRUG, aspirin, null);
		allergies.add(new Allergy(null, allergen1, null, null, null));
		when(ps.getAllergies(any(Patient.class))).thenReturn(allergies);
		
		Allergen duplicateAllergen = new Allergen(AllergenType.FOOD, aspirin, null);
		Allergy allergy = new Allergy(mock(Patient.class), duplicateAllergen, null, null, null);
		Errors errors = new BindException(allergy, "allergy");
		validator.validate(allergy, errors);
		assertTrue(errors.hasFieldErrors("allergen"));
		assertEquals("allergyapi.message.duplicateAllergen", errors.getFieldError("allergen").getCode());
	}