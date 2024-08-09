@Override
	public int compareTo(CohortMembership o) {
		int ret = this.getVoided().compareTo(o.getVoided());
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsLowest(
					getCohort() == null ? null : getCohort().getId(),
					o.getCohort() == null ? null : o.getCohort().getId());
		}
		if (ret == 0) {
			ret = this.getPatientId().compareTo(o.getPatientId());
		}
		if (ret == 0) {
			ret = OpenmrsUtil.compare(this.getStartDate(), o.getStartDate());
		}
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsLatest(this.getEndDate(), o.getEndDate());
		}
		return ret;
	}