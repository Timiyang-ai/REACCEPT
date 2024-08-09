public void handle(ConceptName conceptName, User currentUser, Date currentDate, String reason) {
		
		// put Integer conceptNameTagIds onto ConceptNameTags that are missing them
		if (conceptName.getTags() != null) {
			for (ConceptNameTag tag : conceptName.getTags()) {
				if (tag.getConceptNameTagId() == null) {
					ConceptNameTag possibleReplacementTag = Context.getConceptService()
					        .getConceptNameTagByName(tag.getTag());
					if (possibleReplacementTag != null) {
						conceptName.removeTag(tag);
						conceptName.addTag(possibleReplacementTag);
					}
				}
			}
		}
	}