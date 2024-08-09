@Test
	public final void testGetInstanceSingleObjectReuse() {
		OsmUser user1 = OsmUser.getInstance("aUser", 12);
		// create another one to make it actually do some work
		OsmUser.getInstance("bUser", 14);
		OsmUser user2 = OsmUser.getInstance("aUser", 12);
		assertEquals("Objects are not equal", user1, user2);
		assertEquals("Hash codes are not equal", user1.hashCode(), user2.hashCode());
	}