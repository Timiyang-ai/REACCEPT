@Authorized(value = { HL7Constants.PRIV_UPDATE_HL7_IN_QUEUE, HL7Constants.PRIV_ADD_HL7_IN_QUEUE }, requireAll = false)
	public HL7InQueue saveHL7InQueue(HL7InQueue hl7InQueue) throws APIException;