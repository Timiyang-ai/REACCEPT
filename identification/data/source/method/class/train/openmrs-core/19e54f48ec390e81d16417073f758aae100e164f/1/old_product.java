@Override
	public int compareTo(PatientState o) {
		int result = OpenmrsUtil.compareWithNullAsEarliest(getStartDate(), o.getStartDate());
		if (result == 0) {
			result = OpenmrsUtil.compareWithNullAsLatest(getEndDate(), o.getEndDate());
		}
		return result;
	}