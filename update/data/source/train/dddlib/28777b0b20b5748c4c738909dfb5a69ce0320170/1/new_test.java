@Test
    public void testFindStoredEventsBetween() {
        System.out.println("findStoredEventsBetween");
        Date occurredFrom = null;
        Date occurredTo = null;
        HibernateEventStore instance = new HibernateEventStore();
        List<StoredEvent> expResult = null;
        List<StoredEvent> result = instance.findStoredEventsBetween(occurredFrom, occurredTo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }