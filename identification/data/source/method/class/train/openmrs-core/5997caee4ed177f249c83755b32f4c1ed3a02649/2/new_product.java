@Override
	public Result get(int index) {
		if (isSingleResult()) {
			return (index == 0 ? this : emptyResult);
		}
		
		if (index >= this.size()) {
			return emptyResult;
		}
		return super.get(index);
	}