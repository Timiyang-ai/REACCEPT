@Override
    public Result get(int index) {
		if (isSingleResult())
			return (index == 0 ? this : emptyResult);

		return super.get(index);
	}