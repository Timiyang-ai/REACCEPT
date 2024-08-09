	@Test
	public void saveHL7InQueue_shouldAddGeneratedUuidIfUuidIsNull() {
		HL7InQueue hl7 = new HL7InQueue();
		
		hl7.setHL7Data("dummy data");
		hl7.setHL7Source(new HL7Source(1));
		hl7.setHL7SourceKey("a random key");
		hl7.setMessageState(HL7Constants.HL7_STATUS_PROCESSING);
		
		Context.getHL7Service().saveHL7InQueue(hl7);
		Assert.assertNotNull(hl7.getUuid());
	}