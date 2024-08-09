@Test
    public void testSetInput0() {
        Source s = new Source();
        List<String> values = s.getAllValues( OPT.INPUT );
        assertListMatch( new String[] {}, new String[] {}, 0, values );
    }