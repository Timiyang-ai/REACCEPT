@Test
	public void testValidateCode_PreExpansionAgainstHugeValueSet() throws Exception {
		myDaoConfig.setPreExpandValueSets(true);

		// Add a bunch of codes
		CustomTerminologySet codesToAdd = new CustomTerminologySet();
		for (int i = 0; i < 100; i++) {
			codesToAdd.addRootConcept("CODE" + i, "Display " + i);
		}
		myTermCodeSystemStorageSvc.applyDeltaCodeSystemsAdd("http://loinc.org", codesToAdd);

		// Create a valueset
		ValueSet vs = new ValueSet();
		vs.setUrl("http://example.com/fhir/ValueSet/observation-vitalsignresult");
		vs.getCompose().addInclude().setSystem("http://loinc.org");
		myValueSetDao.create(vs);
		myTermReadSvc.preExpandDeferredValueSetsToTerminologyTables();

		await().until(()->myTermReadSvc.isValueSetPreExpandedForCodeValidation(vs));

		// Load the profile, which is just the Vital Signs profile modified to accept all loinc codes
		// and not just certain ones
		StructureDefinition profile = loadResourceFromClasspath(StructureDefinition.class, "/r4/profile-vitalsigns-all-loinc.json");
		myStructureDefinitionDao.create(profile, mySrd);

		Observation obs = new Observation();
		obs.getMeta().addProfile("http://example.com/fhir/StructureDefinition/vitalsigns-2");
		obs.getText().setDivAsString("<div>Hello</div>");
		obs.getCategoryFirstRep().addCoding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category").setCode("vital-signs");
		obs.setSubject(new Reference("Patient/123"));
		obs.addPerformer(new Reference("Practitioner/123"));
		obs.setEffective(DateTimeType.now());
		obs.setStatus(ObservationStatus.FINAL);
		obs.setValue(new StringType("This is the value"));

		OperationOutcome oo;

		// Valid code
		obs.getText().setStatus(Narrative.NarrativeStatus.GENERATED);
		obs.getCode().getCodingFirstRep().setSystem("http://loinc.org").setCode("CODE3").setDisplay("Display 3");
		oo = validateAndReturnOutcome(obs);
		assertEquals(encode(oo), "No issues detected during validation", oo.getIssueFirstRep().getDiagnostics());

		// Invalid code
		obs.getText().setStatus(Narrative.NarrativeStatus.GENERATED);
		obs.getCode().getCodingFirstRep().setSystem("http://loinc.org").setCode("non-existing-code").setDisplay("Display 3");
		oo = validateAndReturnOutcome(obs);
		assertEquals(encode(oo), "None of the codes provided are in the value set http://example.com/fhir/ValueSet/observation-vitalsignresult (http://example.com/fhir/ValueSet/observation-vitalsignresult, and a code from this value set is required) (codes = http://loinc.org#non-existing-code)", oo.getIssueFirstRep().getDiagnostics());

		// Valid code with no system
		obs.getText().setStatus(Narrative.NarrativeStatus.GENERATED);
		obs.getCode().getCodingFirstRep().setSystem(null).setCode("CODE3").setDisplay("Display 3");
		oo = validateAndReturnOutcome(obs);
		assertEquals(encode(oo), "None of the codes provided are in the value set http://example.com/fhir/ValueSet/observation-vitalsignresult (http://example.com/fhir/ValueSet/observation-vitalsignresult, and a code from this value set is required) (codes = null#CODE3)", oo.getIssueFirstRep().getDiagnostics());

		// Valid code with wrong system
		obs.getText().setStatus(Narrative.NarrativeStatus.GENERATED);
		obs.getCode().getCodingFirstRep().setSystem("http://foo").setCode("CODE3").setDisplay("Display 3");
		oo = validateAndReturnOutcome(obs);
		assertEquals(encode(oo), "Code http://foo/CODE3 was not validated because the code system is not present", oo.getIssueFirstRep().getDiagnostics());

		// Code that exists but isn't in the valueset
		obs.getText().setStatus(Narrative.NarrativeStatus.GENERATED);
		obs.getCode().getCodingFirstRep().setSystem("http://terminology.hl7.org/CodeSystem/observation-category").setCode("vital-signs").setDisplay("Display 3");
		oo = validateAndReturnOutcome(obs);
		assertEquals(encode(oo), "None of the codes provided are in the value set http://example.com/fhir/ValueSet/observation-vitalsignresult (http://example.com/fhir/ValueSet/observation-vitalsignresult, and a code from this value set is required) (codes = http://terminology.hl7.org/CodeSystem/observation-category#vital-signs)", oo.getIssueFirstRep().getDiagnostics());

		// Invalid code in built-in VS/CS
		obs.getText().setStatus(Narrative.NarrativeStatus.GENERATED);
		obs.getCode().getCodingFirstRep().setSystem("http://loinc.org").setCode("CODE3").setDisplay("Display 3");
		obs.getCategoryFirstRep().addCoding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category").setCode("FOO");
		oo = validateAndReturnOutcome(obs);
		assertEquals(encode(oo), "Unknown code: http://terminology.hl7.org/CodeSystem/observation-category / FOO", oo.getIssueFirstRep().getDiagnostics());

	}