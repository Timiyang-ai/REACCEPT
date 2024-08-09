	@Test
	public void hasGroupMembers_shouldReturnTrueIfThisObsHasGroupMembersBasedOnParameter() throws Exception {
		Obs parent = new Obs(5);
		Obs child = new Obs(33);
		child.setVoided(true);
		parent.addGroupMember(child); // Only contains 1 voided child
		assertTrue("When checking for all members, should return true", parent.hasGroupMembers(true));
		assertFalse("When checking for non-voided, should return false", parent.hasGroupMembers(false));
		assertFalse("Default should check for non-voided", parent.hasGroupMembers());
	}