public void handle(Retireable retireableObject, User retiringUser, Date origParentRetiredDate, String unused) {
		
		// only act on retired objects
		if (retireableObject.isRetired() && (origParentRetiredDate == null || origParentRetiredDate.equals(retireableObject.getDateRetired()))) {
			// only act on retired objects that match the same date retired as the parent
				retireableObject.setRetired(false);
				retireableObject.setRetiredBy(null);
				retireableObject.setDateRetired(null);
				retireableObject.setRetireReason(null);
		}
	}