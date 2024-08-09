@Test
	public void testGetReferences()
	{
		final PartitionerConfig config = PartitionerConfig.builder()
				.newLine().setTableName("ABC")
				.newRef().setReferencedTableName("MNO").setReferencingColumnName("ABC_columnName").setReferencedConfigLine("MNO").endRef()
				.newLine().setTableName("MNO") // this one is referenced by both "ABC" and "XYZ"
				.newLine().setTableName("XYZ")
				.newRef().setReferencedTableName("MNO").setReferencingColumnName("XYZ_columnName").setReferencedConfigLine("MNO").endRef()
				.endLine()
				.build();

		final List<PartionerConfigReference> referencingLines = config.getReferences("mno"); // also need to work with different case

		assertThat(referencingLines.size(), is(2));

		final Optional<PartionerConfigReference> refLine1 = referencingLines.stream().filter(r -> r.getReferencingColumnName().equals("ABC_columnName")).findFirst();
		assertThat(refLine1.isPresent(), is(true));
		assertThat(refLine1.get().getParent().getTableName(), is("ABC"));
		assertThat(refLine1.get().getReferencedTableName(), is("MNO"));

		final Optional<PartionerConfigReference> refLine2 = referencingLines.stream().filter(r -> r.getReferencingColumnName().equals("XYZ_columnName")).findFirst();
		assertThat(refLine2.isPresent(), is(true));
		assertThat(refLine2.get().getParent().getTableName(), is("XYZ"));
		assertThat(refLine2.get().getReferencedTableName(), is("MNO"));
	}