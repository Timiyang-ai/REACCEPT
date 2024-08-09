@Override
	public int compareTo(CohortMembership o) {
		int ret = this.getVoided().compareTo(o.getVoided());
		if (ret == 0) {
			ret = -OpenmrsUtil.compareWithNullAsLatest(this.getEndDate(), o.getEndDate());
		}
		if (ret == 0) {
			ret = -OpenmrsUtil.compareWithNullAsEarliest(this.getStartDate(), o.getStartDate());
		}
		if (ret == 0) {
			ret = this.getPatientId().compareTo(o.getPatientId());
		}
		if (ret == 0) {
			ret = this.getUuid().compareTo(o.getUuid());
		}
		return ret;
	}