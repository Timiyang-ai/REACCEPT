@Override
	public int compareTo(CohortMembership o) {
		int ret = -1;
		if (Objects.equals(this.getPatientId(), o.getPatientId())
				&& Objects.equals(this.getCohort().getCohortId(), o.getCohort().getCohortId())
				&& this.getStartDate().equals(o.getStartDate())
				&& OpenmrsUtil.compare(this.getStartDate(), o.getStartDate()) == 0
				&& ((this.getEndDate() != null && o.getEndDate() != null
				&& OpenmrsUtil.compare(this.getEndDate(), o.getEndDate()) == 0)
				|| (this.getEndDate() == null && o.getEndDate() == null))) {
			ret = 0;
		} else if (this.isActive() && !o.isActive()) {
			ret = -1;
		} else if (!this.isActive() && o.isActive()) {
			ret = 1;
		}
		return ret;
	}