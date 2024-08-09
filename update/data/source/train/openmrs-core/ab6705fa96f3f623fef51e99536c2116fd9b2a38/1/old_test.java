@Test
	public void addGroupMember_shouldReturnTrueWhenANewObsIsAddedAsAMember() throws Exception {
		Obs obs = new Obs();
		Obs member1 = new Obs();
		obs.addGroupMember(member1);
		assertTrue(obs.isDirty());
		resetObs(obs);
		Obs member2 = new Obs();
		obs.addGroupMember(member2);
		assertTrue(obs.isDirty());
	}