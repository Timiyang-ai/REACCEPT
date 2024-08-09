@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BaseOpenmrsObject))
			return false;
		BaseOpenmrsObject other = (BaseOpenmrsObject) obj;
		if (uuid == null)
			return false;
		return uuid.equals(other.uuid);
	}