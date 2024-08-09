public void handle(Retireable retireableObject, User retiringUser, Date origParentRetiredDate, String unused) {
		
		// only act on retired objects
		if (retireableObject.isRetired()) {
			// only act on retired objects that match the same date retired as the parent
			if (origParentRetiredDate == null || origParentRetiredDate.equals(retireableObject.getDateRetired())) {
				retireableObject.setRetired(false);
				retireableObject.setRetiredBy(null);
				retireableObject.setDateRetired(null);
				retireableObject.setRetireReason(null);
			}
		}
	}