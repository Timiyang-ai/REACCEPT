@Test
	public void setGroupMembers_shouldMarkTheExistingObsAsDirtyWhenTheSetIsChangedFromNullToANonEmptyOne() throws Exception {
		Obs obs = new Obs(5);
		assertNull(Obs.class.getDeclaredField("groupMembers").get(obs));
		Set members = new HashSet<>();
		members.add(new Obs());
		obs.setGroupMembers(members);
		assertTrue(obs.isDirty());
	}