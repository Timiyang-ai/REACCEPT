@Test
    public void testInsertGelfMessage() throws Exception {
        GELFMessage message = new GELFMessage();
        message.shortMessage = "gelftest";
        message.fullMessage = "full gelftest\nstuff";
        message.level = 1;
        message.type = 8;
        message.host = "junit-test";
        message.file = "junit-testfile";
        message.line = 9001;

        // Insert the message.
        MongoBridge instance = new MongoBridge();
        instance.insertGelfMessage(message);

        // Fetch the event and compare
        DBCollection coll = instance.getMessagesColl();
        long count = coll.getCount();
        assertTrue(count == 1);

        DBObject res = coll.findOne();
        assertEquals(res.get("message"), "gelftest");
        assertEquals(res.get("full_message"), "full gelftest\nstuff");
        assertEquals(res.get("level"), 1);
        assertEquals(res.get("type"), 8);
        assertEquals(res.get("host"), "junit-test");
        assertEquals(res.get("file"), "junit-testfile");
        assertEquals(res.get("line"), 9001);
    }