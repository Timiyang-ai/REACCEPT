@Override
	public void handle(ConceptName conceptName, User currentUser, Date currentDate, String reason) {
		
		// put Integer conceptNameTagIds onto ConceptNameTags that are missing them
		if (conceptName.getTags() != null) {
			Collection<ConceptNameTag> replacementTags = new ArrayList<>();
			
			Iterator<ConceptNameTag> tagsIt = conceptName.getTags().iterator();
			while (tagsIt.hasNext()) {
				ConceptNameTag tag = tagsIt.next();
				
				if (tag.getConceptNameTagId() == null) {
					ConceptNameTag replacementTag = Context.getConceptService().getConceptNameTagByName(tag.getTag());
					
					if (replacementTag != null) {
						tagsIt.remove();
						replacementTags.add(replacementTag);
					}
				}
			}
			
			if (!replacementTags.isEmpty()) {
				conceptName.getTags().addAll(replacementTags);
			}
		}
	}