public void handle(Voidable voidableObject, User voidingUser, Date origParentVoidedDate, String unused) {
		
		// only operate on voided objects
		if (voidableObject.isVoided()
		        && (origParentVoidedDate == null || origParentVoidedDate.equals(voidableObject.getDateVoided()))) {
			
			// only unvoid objects that were voided at the same time as the parent object
			voidableObject.setVoided(false);
			voidableObject.setVoidedBy(null);
			voidableObject.setDateVoided(null);
			voidableObject.setVoidReason(null);
		}
	}