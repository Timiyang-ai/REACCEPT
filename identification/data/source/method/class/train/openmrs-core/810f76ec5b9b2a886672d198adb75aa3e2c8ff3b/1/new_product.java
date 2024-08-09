public Object toObject() {
		if (isSingleResult())
			return resultObject;
		if (this.size() == 1)
			return this.get(0).toObject();
		throw new LogicException("This result represents more than one result, you cannot call toObject on multiple results");
	}