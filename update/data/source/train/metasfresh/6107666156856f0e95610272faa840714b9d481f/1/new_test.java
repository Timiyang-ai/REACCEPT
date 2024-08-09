@Test
	public void filterOrgs_EmptyRuleShallNotInfuenceTheResult()
	{
		final CompositeSecurityRule rule = new CompositeSecurityRule();
		final String tableName = "DummyTableName"; // does not matter
		final boolean rw = false; // does not matter

		{
			final Set<OrgId> expected = asOrgIdsSet();
			final Set<OrgId> actual = asOrgIdsSet();
			rule.filterOrgs(role, tableName, rw, actual);
			Assert.assertEquals("Org IDs shall not be modified", expected, actual);
		}
		{
			final Set<OrgId> expected = asOrgIdsSet(1);
			final Set<OrgId> actual = asOrgIdsSet(1);
			rule.filterOrgs(role, tableName, rw, actual);
			Assert.assertEquals("Org IDs shall not be modified", expected, actual);
		}
		{
			final Set<OrgId> expected = asOrgIdsSet(1, 2, 3);
			final Set<OrgId> actual = asOrgIdsSet(1, 2, 3);
			rule.filterOrgs(role, tableName, rw, actual);
			Assert.assertEquals("Org IDs shall not be modified", expected, actual);
		}
	}