@Deprecated
	public void setMemberIds(Set<Integer> memberIds) {
		if (getMemberships().isEmpty()) {
		    Date startDate = new Date();
			for (Integer id : memberIds) {
			    CohortMembership membership = new CohortMembership(id, startDate);
                membership.setCohort(this);
                getMemberships().add(membership);
			}
		}
		else {
			throw new IllegalArgumentException("since 2.1.0 cohorts are more complex than just a set of patient ids");
		}
	}