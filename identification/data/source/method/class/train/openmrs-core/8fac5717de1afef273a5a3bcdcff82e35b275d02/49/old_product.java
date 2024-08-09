public void addGroupMember(Obs member) {
		if (member == null)
			return;
		
		if (getGroupMembers() == null)
			groupMembers = new HashSet<Obs>();
		
		// a quick sanity check to make sure someone isn't adding
		// itself to the group
		if (member.equals(this))
			throw new APIException("An obsGroup cannot have itself as a mentor. obsGroup: " + this
			        + " obsMember attempting to add: " + member);
		
		member.setObsGroup(this);
		groupMembers.add(member);
	}