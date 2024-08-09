	@Test
	public void processMessage_shouldCreateEncounterAndObsFromHl7Message() throws Exception {
		ObsService obsService = Context.getObsService();
		
		String hl7string = "MSH|^~\\&|FORMENTRY|AMRS.ELD|HL7LISTENER|AMRS.ELD|20080226102656||ORU^R01|JqnfhKKtouEz8kzTk6Zo|P|2.5|1||||||||16^AMRS.ELD.FORMID\r"
		        + "PID|||3^^^^||John3^Doe^||\r"
		        + "PV1||O|1^Unknown Location||||1^Super User (1-8)|||||||||||||||||||||||||||||||||||||20080212|||||||V\r"
		        + "ORC|RE||||||||20080226102537|1^Super User\r"
		        + "OBR|1|||1238^MEDICAL RECORD OBSERVATIONS^99DCT\r"
		        + "OBX|1|NM|5497^CD4, BY FACS^99DCT||450|||||||||20080206\r"
		        + "OBX|2|DT|5096^RETURN VISIT DATE^99DCT||20080229|||||||||20080212";
		Message hl7message = parser.parse(hl7string);
		router.processMessage(hl7message);
		
		Patient patient = new Patient(3);
		
		// check for an encounter
		List<Encounter> encForPatient3 = Context.getEncounterService().getEncountersByPatient(patient);
		assertNotNull(encForPatient3);
		assertTrue("There should be an encounter created", encForPatient3.size() == 1);
		
		// check for any obs
		List<Obs> obsForPatient3 = obsService.getObservationsByPerson(patient);
		assertNotNull(obsForPatient3);
		assertTrue("There should be some obs created for #3", obsForPatient3.size() > 0);
		
		// check for the return visit date obs
		Concept returnVisitDateConcept = new Concept(5096);
		Calendar cal = Calendar.getInstance();
		cal.set(2008, Calendar.FEBRUARY, 29, 0, 0, 0);
		Date returnVisitDate = cal.getTime();
		List<Obs> returnVisitDateObsForPatient3 = obsService.getObservationsByPersonAndConcept(patient,
		    returnVisitDateConcept);
		assertEquals("There should be a return visit date", 1, returnVisitDateObsForPatient3.size());
		
		Obs firstObs = (Obs) returnVisitDateObsForPatient3.toArray()[0];
		cal.setTime(firstObs.getValueDatetime());
		Date firstObsValueDatetime = cal.getTime();
		assertEquals("The date should be the 29th", returnVisitDate.toString(), firstObsValueDatetime.toString());
		
	}