@Override
	public int hashCode() {
		if (getUuid() == null) {
			return super.hashCode();
		}
		return getUuid().hashCode();
	}