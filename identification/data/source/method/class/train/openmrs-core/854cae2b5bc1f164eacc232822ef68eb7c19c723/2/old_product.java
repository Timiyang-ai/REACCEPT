public void handle(Voidable voidableObject, User voidingUser, Date voidedDate, String voidReason) {
		
		if (voidReason == null || voidReason.equals(""))
			throw new IllegalArgumentException("The 'reason' argument is required");
		
		// skip over all work if the object is already voided
		if (!voidableObject.isVoided() || voidableObject.getVoidedBy() == null) {
			
			voidableObject.setVoided(true);
			voidableObject.setVoidReason(voidReason);
			
			if (voidableObject.getVoidedBy() == null) {
				voidableObject.setVoidedBy(voidingUser);
			}
			if (voidableObject.getDateVoided() == null) {
				voidableObject.setDateVoided(voidedDate);
			}
		}
	}