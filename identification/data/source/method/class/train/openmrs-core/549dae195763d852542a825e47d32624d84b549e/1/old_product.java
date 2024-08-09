@Override
	public String toString() {
		if (uuid != null) {
			return getClass().getName() + "[" + uuid + "]";
		} else {
			return super.toString();
		}
	}