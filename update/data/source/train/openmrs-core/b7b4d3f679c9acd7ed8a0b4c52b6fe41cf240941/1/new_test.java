@Test
	@Verifies(value = "should parse the given string into Message", method = "parseHL7String(String)")
	public void parseHL7String_shouldParseTheGivenStringIntoMessage() throws Exception {
		HL7Service hl7service = Context.getHL7Service();
		Message message = hl7service
		        .parseHL7String("MSH|^~\\&|FORMENTRY|AMRS.ELD|HL7LISTENER|AMRS.ELD|20080226102656||ORU^R01|JqnfhKKtouEz8kzTk6Zo|P|2.5|1||||||||16^AMRS.ELD.FORMID\rPID|||3^^^^||John3^Doe^||\rPV1||O|1^Unknown Location||||1^Super User (1-8)|||||||||||||||||||||||||||||||||||||20080212|||||||V\rORC|RE||||||||20080226102537|1^Super User\rOBR|1|||1238^MEDICAL RECORD OBSERVATIONS^99DCT\rOBX|1|NM|5497^CD4, BY FACS^99DCT||450|||||||||20080206\rOBX|2|DT|5096^RETURN VISIT DATE^99DCT||20080229|||||||||20080212");
		Assert.assertNotNull(message);
	}