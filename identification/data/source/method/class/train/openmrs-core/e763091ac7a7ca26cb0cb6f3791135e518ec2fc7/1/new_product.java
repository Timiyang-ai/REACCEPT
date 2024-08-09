public void setAttribute(A attribute) {
		if (getAttributes() == null) {
			addAttribute(attribute);
			return;
		}
		
		if (getActiveAttributes(attribute.getAttributeType()).size() == 1) {
			A existing = getActiveAttributes(attribute.getAttributeType()).get(0);
			if (existing.getValue().equals(attribute.getValue())) {
				// do nothing, since the value is already as-specified
			} else {
				if (existing.getId() != null)
					existing.setVoided(true);
				else
					getAttributes().remove(existing);
				getAttributes().add(attribute);
				attribute.setOwner(this);
			}
			
		} else {
			for (A existing : getActiveAttributes(attribute.getAttributeType()))
				if (existing.getAttributeType().equals(attribute.getAttributeType()))
					if (existing.getId() != null)
						existing.setVoided(true);
					else
						getAttributes().remove(existing);
			getAttributes().add(attribute);
			attribute.setOwner(this);
		}
	}