	@Test
	public void resolveUserId_shouldReturnNullForAmbiguousUsersUsingFirstAndLastNameGivenUserIDIsNull() throws HL7Exception {
		HL7Service hl7service = Context.getHL7Service();
		executeDataSet(XML_FILENAME);
		//construct a message such that id Number at ORC is null
		Message message = hl7service
								  .parseHL7String("MSH|^~\\&|FORMENTRY|AMRS.ELD|HL7LISTENER|AMRS.ELD|20080226102656||ORU^R01|JqnfhKKtouEz8kzTk6Zo|P|2.5|1||||||||16^AMRS.ELD.FORMID\r" +
												  "PID|||3^^^^||John3^Doe^||\r" +
												  "NK1|1|Hornblower^Horatio^L|2B^Sibling^99REL||||||||||||M|19410501|||||||||||||||||1000^^^L^PN||||\r" +
												  "PV1||O|99999^0^0^0&Unknown&0||||^Super User (1-8)|||||||||||||||||||||||||||||||||||||20080212|||||||V\r" +
												  "ORC|RE||||||||20080226102537|^User^Super\r" +
												  "OBR|1|||1238^MEDICAL RECORD OBSERVATIONS^99DCT\r" +
												  "OBX|1|NM|5497^CD4, BY FACS^99DCT||450|||||||||20080206\r" +
												  "OBX|2|DT|5096^RETURN VISIT DATE^99DCT||20080229|||||||||20080212");
		ORU_R01 oru = (ORU_R01) message;
		ORC orc = oru.getPATIENT_RESULT().getORDER_OBSERVATION().getORC();
		XCN xcn = orc.getEnteredBy(0);
		//userId should be null since there exist two ambiguous users that has givename=Super and lastname=User.
		Integer userId = hl7service.resolveUserId(xcn);
		Assert.assertNull(userId);
	}