@Test
    public void testParseDateTime_noTime_noTimeZone() throws ExecException {

        Tuple t1 = TupleFactory.getInstance().newTuple(1);
        t1.set(0, "2010-04-15");

        // Time zone is preserved.
        assertEquals(new DateTime(2010, 4, 15, 0, 0, 0, 0, DateTimeZone.getDefault()), ISOHelper.parseDateTime(t1));  
    }