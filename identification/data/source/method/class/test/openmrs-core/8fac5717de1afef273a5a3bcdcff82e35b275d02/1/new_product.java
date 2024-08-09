public void addGroupMember(Obs member) {
		if (member == null) {
			return;
		}
		
		if (getGroupMembers() == null) {
			groupMembers = new HashSet<>();
		}
		
		// a quick sanity check to make sure someone isn't adding
		// itself to the group
		if (member.equals(this)) {
			throw new APIException("Obs.error.groupCannotHaveItselfAsAMentor", new Object[] { this, member });
		}
		
		member.setObsGroup(this);
		groupMembers.add(member);
	}