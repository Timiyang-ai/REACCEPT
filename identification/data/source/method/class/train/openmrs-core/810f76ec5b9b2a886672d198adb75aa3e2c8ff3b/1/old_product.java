public Object toObject() {
		if (this.size() < 1)
			return null;
		if (this.size() == 1)
			return this.get(0).toObject();
		return this.toArray();
	}