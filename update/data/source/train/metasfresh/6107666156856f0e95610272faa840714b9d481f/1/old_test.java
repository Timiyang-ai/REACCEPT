@Test
	public void filterOrgs_EmptyRuleShallNotInfuenceTheResult()
	{
		final CompositeSecurityRule rule = new CompositeSecurityRule();
		final String tableName = "DummyTableName"; // does not matter
		final boolean rw = false; // does not matter

		{
			final Set<Integer> expected = asHashSet();
			final Set<Integer> actual = asHashSet();
			rule.filterOrgs(role, tableName, rw, actual);
			Assert.assertEquals("Org IDs shall not be modified", expected, actual);
		}
		{
			final Set<Integer> expected = asHashSet(1);
			final Set<Integer> actual = asHashSet(1);
			rule.filterOrgs(role, tableName, rw, actual);
			Assert.assertEquals("Org IDs shall not be modified", expected, actual);
		}
		{
			final Set<Integer> expected = asHashSet(1, 2, 3);
			final Set<Integer> actual = asHashSet(1, 2, 3);
			rule.filterOrgs(role, tableName, rw, actual);
			Assert.assertEquals("Org IDs shall not be modified", expected, actual);
		}
	}