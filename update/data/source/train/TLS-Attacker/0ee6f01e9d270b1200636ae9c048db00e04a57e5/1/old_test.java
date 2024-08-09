@Test
    public void testContainsModifiableVariableModification() {
	ClientHelloMessage ch = new ClientHelloMessage();
	assertFalse("This ClientHello message contains no modification",
		TlsContextAnalyzer.containsModifiableVariableModification(ch));
	ch.setCipherSuiteLength(2);
	assertFalse("This ClientHello message contains no modification",
		TlsContextAnalyzer.containsModifiableVariableModification(ch));
	ModifiableInteger length = new ModifiableInteger();
	length.setModification(IntegerModificationFactory.add(1));
	ch.setCipherSuiteLength(length);
	assertTrue("This ClientHello message contains a modification in the CipherSuite Length variable",
		TlsContextAnalyzer.containsModifiableVariableModification(ch));

	ch = new ClientHelloMessage();
	List<Record> records = new LinkedList<>();
	Record r = new Record();
	r.setLength(length);
	records.add(r);
	ch.setRecords(records);
	assertTrue("This ClientHello message contains a modification in the record Length variable",
		TlsContextAnalyzer.containsModifiableVariableModification(ch));
    }