@Test
    public void testSetInput0() throws SchemagenOptionsConfigurationException {
        SchemagenOptions so = new SchemagenOptions(null, new Source()); 
        List<String> values = so.getAllValues( OPT.INPUT );
        assertListMatch( new String[] {}, new String[] {}, 0, values );
    }