@Test
	public void testGetReferences()
	{
		final PartitionerConfig config = PartitionerConfig.builder()
				.line("ABC")
				.ref().setReferencedTableName("MNO").setReferencingColumnName("ABC_columnName").endRef()
				.line("MNO") // this one is referenced by both "ABC" and "XYZ"
				.line("XYZ")
				.ref().setReferencedTableName("MNO").setReferencingColumnName("XYZ_columnName").endRef()
				.endLine()
				.build();

		final List<PartitionerConfigReference> referencingLines = config.getReferences("MNO");

		assertThat(referencingLines.size(), is(2));

		final Optional<PartitionerConfigReference> refLine1 = referencingLines.stream().filter(r -> r.getReferencingColumnName().equals("ABC_columnName")).findFirst();
		assertThat(refLine1.isPresent(), is(true));
		assertThat(refLine1.get().getParent().getTableName(), is("ABC"));
		assertThat(refLine1.get().getReferencedTableName(), is("MNO"));

		final Optional<PartitionerConfigReference> refLine2 = referencingLines.stream().filter(r -> r.getReferencingColumnName().equals("XYZ_columnName")).findFirst();
		assertThat(refLine2.isPresent(), is(true));
		assertThat(refLine2.get().getParent().getTableName(), is("XYZ"));
		assertThat(refLine2.get().getReferencedTableName(), is("MNO"));
	}