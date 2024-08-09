@Test
    public void testInsertGelfMessage() throws Exception {
        GELFMessage message = new GELFMessage();
        message.setShortMessage("gelftest");
        message.setFullMessage("full gelftest\nstuff");
        message.setLevel(1);
        message.setHost("junit-test");
        message.setFile("junit-testfile");
        message.setLine(9001);
        message.addAdditionalData("something", "yepp");

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
        assertEquals(res.get("something"), "yepp");
    }