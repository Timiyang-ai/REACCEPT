	@Test
	public void getGroupMembers_shouldGetAllGroupMembersIfPassedTrueAndNonvoidedIfPassedFalse() throws Exception {
		Obs parent = new Obs(1);
		Set<Obs> members = new HashSet<>();
		members.add(new Obs(101));
		members.add(new Obs(103));
		Obs voided = new Obs(99);
		voided.setVoided(true);
		members.add(voided);
		parent.setGroupMembers(members);
		members = parent.getGroupMembers(true);
		assertEquals("set of all members should have length of 3", 3, members.size());
		members = parent.getGroupMembers(false);
		assertEquals("set of non-voided should have length of 2", 2, members.size());
		members = parent.getGroupMembers(); // should be same as false
		assertEquals("default should return non-voided with length of 2", 2, members.size());
	}