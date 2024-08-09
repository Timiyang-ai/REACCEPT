@Override
	public String toString() {
		if (getUuid() != null) {
			return getClass().getName() + "[" + getUuid() + "]";
		} else {
			return super.toString();
		}
	}