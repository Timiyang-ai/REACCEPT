@SuppressWarnings("unchecked")
	@Override
	public void setAttribute(AttrClass attribute) {
		if (getAttributes() == null)
			setAttributes(new LinkedHashSet<AttrClass>());
		// TODO validate
		if (getActiveAttributes(attribute.getAttributeType()).size() == 1) {
			AttrClass existing = getActiveAttributes(attribute.getAttributeType()).get(0);
			if (existing.getSerializedValue().equals(attribute.getSerializedValue())) {
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
			for (AttrClass existing : getActiveAttributes(attribute.getAttributeType()))
				if (existing.getAttributeType().equals(attribute.getAttributeType()))
					if (existing.getId() != null)
						existing.setVoided(true);
					else
						getAttributes().remove(existing);
			getAttributes().add(attribute);
			attribute.setOwner(this);
		}
	}