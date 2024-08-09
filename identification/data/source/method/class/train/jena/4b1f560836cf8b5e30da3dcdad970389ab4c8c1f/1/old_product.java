@Override
	public SelectBuilder setDistinct(boolean state) {
		getSelectHandler().setDistinct(state);
		return this;
	}