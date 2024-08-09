	@Test
	public void resolveLocationId_shouldReturnInternalIdentifierOfLocationIfOnlyLocationNameIsSpecified()
	        throws HL7Exception {
		executeDataSet("org/openmrs/hl7/include/ORUTest-initialData.xml");
		HL7Service hl7service = Context.getHL7Service();
		Message message = hl7service
		        .parseHL7String("MSH|^~\\&|FORMENTRY|AMRS.ELD|HL7LISTENER|AMRS.ELD|20080226102656||ORU^R01|JqnfhKKtouEz8kzTk6Zo|P|2.5|1||||||||16^AMRS.ELD.FORMID\r"
		                + "PID|||3^^^^||John3^Doe^||\r"
		                + "NK1|1|Hornblower^Horatio^L|2B^Sibling^99REL||||||||||||M|19410501|||||||||||||||||1000^^^L^PN||||\r"
		                + "PV1||O|99999^0^0^0&Test Location&0||||1^Super User (1-8)|||||||||||||||||||||||||||||||||||||20080212|||||||V\r"
		                + "ORC|RE||||||||20080226102537|1^Super User\r"
		                + "OBR|1|||1238^MEDICAL RECORD OBSERVATIONS^99DCT\r"
		                + "OBX|1|NM|5497^CD4, BY FACS^99DCT||450|||||||||20080206\r"
		                + "OBX|2|DT|5096^RETURN VISIT DATE^99DCT||20080229|||||||||20080212");
		ORU_R01 oru = (ORU_R01) message;
		PV1 pv1 = oru.getPATIENT_RESULT().getPATIENT().getVISIT().getPV1();
		Assert.assertNotNull("PV1 parsed as null", pv1);
		PL hl7Location = pv1.getAssignedPatientLocation();
		Integer locationId = hl7service.resolveLocationId(hl7Location);
		Assert.assertEquals("Resolved and given locationId shoud be equals", Integer.valueOf(1), locationId);
	}