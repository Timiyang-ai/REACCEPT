public List<PartitionerConfigReference> getReferences(final String tableName)
	{
		final List<PartitionerConfigReference> references = getLines().stream()
				.flatMap(line -> line.getReferences().stream()) // get a stream of all references
				.filter(ref -> tableName.equalsIgnoreCase(ref.getReferencedTableName())) // filter those who refer to 'tablename'
				.collect(Collectors.toList());
		return references;
	}