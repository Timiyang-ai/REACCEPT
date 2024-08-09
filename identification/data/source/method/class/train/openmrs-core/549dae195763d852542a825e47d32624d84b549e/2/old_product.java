@Override
	public int hashCode() {
		if (uuid == null)
			return super.hashCode();
		return uuid.hashCode();
	}