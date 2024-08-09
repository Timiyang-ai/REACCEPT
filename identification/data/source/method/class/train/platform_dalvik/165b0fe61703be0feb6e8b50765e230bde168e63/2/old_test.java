@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "entrySet",
        args = {}
    )
    public void test_entrySet() {
        // Test for method java.util.Set java.util.Hashtable.entrySet()
        Set s = ht10.entrySet();
        Set s2 = new HashSet();
        Iterator i = s.iterator();
        while (i.hasNext())
            s2.add(((Map.Entry) i.next()).getValue());
        Enumeration e = elmVector.elements();
        while (e.hasMoreElements())
            assertTrue("Returned incorrect entry set", s2.contains(e
                    .nextElement()));

        assertEquals("Not synchronized", 
                "java.util.Collections$SynchronizedSet", s.getClass().getName());

        boolean exception = false;
        try {
            ((Map.Entry) ht10.entrySet().iterator().next()).setValue(null);
        } catch (NullPointerException e1) {
            exception = true;
        }
        assertTrue(
                "Should not be able to assign null to a Hashtable entrySet() Map.Entry",
                exception);
    }