@Test
    public void testEquals() {
        //contruct the event
        Payload event = new Payload();
        event.addStatement(Statement.AND, "number", Statement.EQUALS, "1");
        event.addStatement(Statement.AND, "text", Statement.EQUALS, "abc");

        //contruct the trigger
        Payload trigger = new Payload();
        trigger.addStatement(Statement.AND, "number", Statement.EQUALS, "1");
        trigger.addStatement(Statement.AND, "number", Statement.LESS_THAN, "2");
        trigger.addStatement(Statement.AND, "number", Statement.GREATER_THAN, "0");
        trigger.addStatement(Statement.AND, "number", Statement.LESS_EQUAL_THAN, "1");
        trigger.addStatement(Statement.AND, "number", Statement.EQUALS, Statement.ANY);

        boolean expResult = true;
        //compare trigger with events
        boolean result = trigger.equals(event);
        assertEquals(expResult, result);
    }