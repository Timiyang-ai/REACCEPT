public void handle(Voidable voidableObject, User voidingUser, Date origParentVoidedDate, String unused) {
		
		// only operate on voided objects
		if (voidableObject.isVoided()) {
			
			// only unvoid objects that were voided at the same time as the parent object
			if (origParentVoidedDate == null || origParentVoidedDate.equals(voidableObject.getDateVoided())) {
				voidableObject.setVoided(false);
				voidableObject.setVoidedBy(null);
				voidableObject.setDateVoided(null);
				voidableObject.setVoidReason(null);
			}
		}
	}