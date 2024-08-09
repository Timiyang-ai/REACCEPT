@Override
	public Message processMessage(Message message) throws ApplicationException {
		
		if (!(message instanceof ORU_R01))
			throw new ApplicationException("Invalid message sent to ORU_R01 handler");
		
		log.debug("Processing ORU_R01 message");
		
		Message response;
		try {
			ORU_R01 oru = (ORU_R01) message;
			response = processORU_R01(oru);
		}
		catch (ClassCastException e) {
			log.warn("Error casting " + message.getClass().getName() + " to ORU_R01", e);
			throw new ApplicationException("Invalid message type for handler. Error casting " + message.getClass().getName()
			        + " to ORU_R01", e);
		}
		catch (HL7Exception e) {
			log.warn("Error while processing ORU_R01 message", e);
			throw new ApplicationException("Error while processing ORU_R01 message", e);
		}
		
		log.debug("Finished processing ORU_R01 message");
		
		return response;
	}