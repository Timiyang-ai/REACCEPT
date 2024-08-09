public void setMemberIds(Set<Integer> memberIds) {
		Set<Integer> ids = new TreeSet<Integer>(memberIds);
		for (Integer id : ids) {
			addMembership(new CohortMembership(id));
		}
	}