@Test
    public void testAllStoredEventsSince() {
        System.out.println("allStoredEventsSince");
        Date occurredFrom = null;
        HibernateEventStore instance = new HibernateEventStore();
        List<StoredEvent> expResult = null;
        List<StoredEvent> result = instance.allStoredEventsSince(occurredFrom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }