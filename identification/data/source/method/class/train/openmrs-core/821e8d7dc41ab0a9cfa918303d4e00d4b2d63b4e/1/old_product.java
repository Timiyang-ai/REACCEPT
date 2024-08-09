@Override
	public int compareTo(CohortMembership o) {
		int ret = this.getVoided().compareTo(o.getVoided());
		if (ret == 0) {
			ret = -OpenmrsUtil.compareWithNullAsLatest(this.getEndDate(), o.getEndDate());
		}
		if (ret == 0) {
			//When cohort members are built from patient ids, they are given start dates at 
			//the time of instantiation using the CohortMembership(Integer) constructor
			//Since this is done in a loop, the dates will be different but almost the same
			//We assume that the difference will be in seconds, and hence not exceed one minute
			if (this.getStartDate() == null || o.getStartDate() == null || getMinutesBetween(this.getStartDate(), o.getStartDate()) > 1) {
				ret = -OpenmrsUtil.compareWithNullAsEarliest(this.getStartDate(), o.getStartDate());
			}
		}
		if (ret == 0) {
			ret = this.getPatientId().compareTo(o.getPatientId());
		}
		return ret;
	}