	@Test
	public void testgetUserRolesForGroup() {
		Map<String, String> userRolesForGroup = courseManagementGroupProvider.getUserRolesForGroup(PACKED_ID);
		assertNotNull(userRolesForGroup);
		assertEquals(3, userRolesForGroup.size());
		// user1 is in both sections, but it a lower role in the second section. Check that the preferred role
		// wins out.
		assertEquals("maintain", userRolesForGroup.get("user1"));
		assertEquals("maintain", userRolesForGroup.get("user2"));
		assertEquals("access", userRolesForGroup.get("user3"));
		// user4 has a null role, so check we didn't get this one.
		assertNull(userRolesForGroup.get("user4"));
	}