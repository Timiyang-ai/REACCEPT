@Deprecated
	public void setMemberIds(Set<Integer> memberIds) {
		if (getMemberships().size() == 0) {
			for (Integer id : memberIds) {
				addMembership(new CohortMembership(id));
			}
		}
		else {
			throw new IllegalArgumentException("since 2.1.0 cohorts are more complex than just a set of patient ids");
		}
	}