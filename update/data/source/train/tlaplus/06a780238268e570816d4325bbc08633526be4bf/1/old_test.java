@Test
	public void testDeepCopy() {
		final InternTable internTable = new InternTable(2);
		final UniqueString a = internTable.put("a");
		final UniqueString b = internTable.put("b");
		
		final Value aVal = new StringValue("aVal");
		final Value bVal = new StringValue("bVal");
		
		// Create the source to create a deep copy of
		final RecordValue orig = new RecordValue(new UniqueString[] {b, a}, new Value[] {bVal, aVal}, false);
		
		// Verify the mappings in RecordValue are correct
		assertTrue(orig.names[0].equals(b));
		assertTrue(orig.names[1].equals(a));
		assertTrue(orig.values[0].equals(bVal));
		assertTrue(orig.values[1].equals(aVal));
		
		// Make a deep copy of te origina record value
		final RecordValue deepCopy = (RecordValue) orig.deepCopy();
		
		// Normalize the original record value and check its mappings have been
		// re-organized
		orig.deepNormalize();
		assertTrue(orig.names[0].equals(a));
		assertTrue(orig.names[1].equals(b));
		assertTrue(orig.values[0].equals(aVal));
		assertTrue(orig.values[1].equals(bVal));
		
		// Check that the mappings in the deep copy didn't change.
		assertTrue(deepCopy.names[0].equals(b));
		assertTrue(deepCopy.names[1].equals(a));
		assertTrue(deepCopy.values[0].equals(bVal));
		assertTrue(deepCopy.values[1].equals(aVal));
	}