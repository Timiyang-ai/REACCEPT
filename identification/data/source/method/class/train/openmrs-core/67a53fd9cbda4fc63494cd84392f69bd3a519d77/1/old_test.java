@Test
	public void isObsGrouping_shouldIgnoreVoidedObs() throws Exception {
		Obs parent = new Obs(5);
		Obs child = new Obs(33);
		child.setVoided(true);
		parent.addGroupMember(child);
		assertFalse("When checking for Obs grouping, should ignore voided Obs", parent.isObsGrouping());
		child = new Obs(66); //new Child that is non-voided
		parent.addGroupMember(child);
		assertTrue("When there is at least 1 non-voided child, should return True", parent.isObsGrouping());
	}