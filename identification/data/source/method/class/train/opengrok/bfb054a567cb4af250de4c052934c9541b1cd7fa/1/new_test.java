    @Test
    public void parseFileLog() throws Exception {
        String output = "//Path/To/Folder/In/Workspace/Filename\n" +
                "\n" +
                "... #4 change 1234 edit on 2008/08/19 11:30:00 by User@UserWorkspaceName (text)\n" +
                "\n" +
                "        Comment for the change number 4\n" +
                "\n" +
                "... #3 change 5678 edit on 2008/08/19 18:25:38 by ADMIN@UserWorkspaceName (text)\n" +
                "\n" +
                "        Comment for the change\n" +
                "\n" +
                "... #2 change 8765 edit on 2008/08/01 01:00:01 by ADMIN@UserWorkspaceName (text)\n" +
                "\n" +
                "        Comment for the change\n" +
                "\n" +
                "... #1 change 1 add on 2008/07/30 17:45:33 by ADMIN@UserWorkspaceName (text)\n" +
                "\n" +
                "        Comment for the change";

        History result = PerforceHistoryParser.parseFileLog(new StringReader(output));

        assertNotNull(result);
        assertEquals(4, result.getHistoryEntries().size());

        HistoryEntry e1 = result.getHistoryEntries().get(0);
        assertEquals("1234", e1.getRevision());
        assertEquals("User", e1.getAuthor());
        assertEquals(0, e1.getFiles().size());
        assertTrue(e1.getMessage().contains("number 4"));

        HistoryEntry e2 = result.getHistoryEntries().get(1);
        assertNotNull(e2);

        HistoryEntry e3 = result.getHistoryEntries().get(2);
        assertNotNull(e3);

        HistoryEntry e4 = result.getHistoryEntries().get(3);
        assertEquals("1", e4.getRevision());

        // Bug #16660: Months used to be off by one. Verify that they match
        // the dates in the sample output above.
        assertDate(e1, 2008, Calendar.AUGUST, 19);
        assertDate(e2, 2008, Calendar.AUGUST, 19);
        assertDate(e3, 2008, Calendar.AUGUST, 1);
        assertDate(e4, 2008, Calendar.JULY, 30);
    }