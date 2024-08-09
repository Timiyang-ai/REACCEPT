@Test
	public void removeGroupMember_shouldReturnTrueWhenAnObsIsRemoved() throws Exception {
		Obs obs = new Obs(2);
		Obs member = new Obs();
		obs.addGroupMember(member);
		assertTrue(obs.isDirty());
		resetObs(obs);
		obs.removeGroupMember(member);
		assertTrue(obs.isDirty());
	}