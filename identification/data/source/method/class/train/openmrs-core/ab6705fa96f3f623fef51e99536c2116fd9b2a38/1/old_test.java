@Test
	public void setGroupMembers_shouldMarkTheObsAsDirtyWhenTheSetIsChangedFromNullToANonEmptyOne() throws Exception {
		Obs obs = new Obs();
		assertNull(Obs.class.getDeclaredField("groupMembers").get(obs));
		Set members = new HashSet<>();
		members.add(new Obs());
		obs.setGroupMembers(members);
		assertTrue(obs.isDirty());
	}