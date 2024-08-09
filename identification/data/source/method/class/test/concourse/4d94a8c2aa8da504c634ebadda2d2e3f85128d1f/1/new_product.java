@Override
	public boolean remove(String key, TObject value, long record) {
		expectedVersions.add(new KeyInRecordVersionExpectation(key, record));
		return super.remove(key, value, record);
	}