@Override
	public int compareTo(CohortMembership o) {
		if ((this.getVoided() && !o.getVoided()) || (!this.isActive() && o.isActive())) {
			return 1;
		} else if ((!this.getVoided() && o.getVoided()) || (this.isActive() && !o.isActive())) {
			return -1;
		}
		
		int ret = OpenmrsUtil.compareWithNullAsGreatest(this.getCohort().getCohortId(), o.getCohort().getCohortId());
		if (ret != 0) {
			return ret;
		}
		
		ret = this.getPatientId().compareTo(o.getPatientId());
		if (ret != 0) {
			return ret;
		}
		
		ret = OpenmrsUtil.compareWithNullAsEarliest(this.getEndDate(), o.getEndDate());
		if (ret != 0) {
			return ret;
		}
		
		return OpenmrsUtil.compare(this.getStartDate(), o.getStartDate());
	}