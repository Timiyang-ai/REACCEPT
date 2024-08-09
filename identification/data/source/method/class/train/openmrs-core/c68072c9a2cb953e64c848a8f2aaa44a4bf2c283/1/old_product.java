@SuppressWarnings("unchecked")
	public String toString() {
		Object o = getHydratedObject();
		if (o instanceof Attributable)
			return ((Attributable) o).getDisplayString();
		
		return this.value;
	}