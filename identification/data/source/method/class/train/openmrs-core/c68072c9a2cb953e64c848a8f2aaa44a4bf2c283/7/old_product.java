@SuppressWarnings("unchecked")
	public String toString() {
		Object o = getHydratedObject();
		if (o instanceof Attributable)
			return ((Attributable) o).getDisplayString();
		else if (o != null)
			return o.toString();
		
		return this.value;
	}