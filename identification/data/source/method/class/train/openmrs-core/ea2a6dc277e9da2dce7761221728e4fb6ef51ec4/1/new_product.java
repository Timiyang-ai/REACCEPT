@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BaseOpenmrsObject))
			return false;
		BaseOpenmrsObject other = (BaseOpenmrsObject) obj;
		// Need to call getUuid to make sure the hibernate proxy objects return the correct uuid.
		// The private member may not be set for a hibernate proxy.
		if (getUuid() == null)
			return false;
		return getUuid().equals(other.getUuid());
	}