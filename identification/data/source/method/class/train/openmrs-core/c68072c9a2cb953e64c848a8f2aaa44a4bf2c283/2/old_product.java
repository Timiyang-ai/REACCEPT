public String toString() {
		Object o = getHydratedObject();
		if (o instanceof Attributable)
			return "" + o;
		
		return this.value;
	}