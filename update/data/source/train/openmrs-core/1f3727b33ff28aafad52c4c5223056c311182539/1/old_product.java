public void removeGroupMember(Obs member) {
		if (member == null || getGroupMembers() == null) {
			return;
		}
		
		if (groupMembers.remove(member)) {
			member.setObsGroup(null);
			setDirty(true);
		}
	}