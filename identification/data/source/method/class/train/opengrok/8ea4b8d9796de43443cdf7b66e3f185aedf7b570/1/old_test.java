    @Test
    public void parseChanges() throws Exception {
        String output = "Change 1234 on 2008/10/13 11:30:00 by ADMIN@UserWorkspaceName 'Comment given to changelist within single qoutes, this is change one'\n" +
                "Change 6543 on 2008/10/08 18:25:38 by USER@USER_WS 'Comment given to changelist within single qoutes'\n" +
                "Change 7654 on 2008/09/30 01:00:01 by USER@USER_WS 'Comment given to changelist within single qoutes'\n" +
                "Change 2345 on 2008/09/30 17:45:33 by ADMIN@Workspace2 'Comment given to changelist within single qoutes'\n";
        History result = PerforceHistoryParser.parseChanges(new StringReader(output));

        assertNotNull(result);
        assertEquals(4, result.getHistoryEntries().size());

        HistoryEntry e1 = result.getHistoryEntries().get(0);
        assertEquals("1234", e1.getRevision());
        assertEquals("ADMIN", e1.getAuthor());
        assertEquals(0, e1.getFiles().size());
        assertTrue(e1.getMessage().contains("change one"));

        HistoryEntry e2 = result.getHistoryEntries().get(1);
        assertNotNull(e2);

        HistoryEntry e3 = result.getHistoryEntries().get(2);
        assertNotNull(e3);

        HistoryEntry e4 = result.getHistoryEntries().get(3);
        assertEquals("2345", e4.getRevision());

        // Bug #16660: Months used to be off by one. Verify that they match
        // the dates in the sample output above.
        assertDate(e1, 2008, Calendar.OCTOBER, 13);
        assertDate(e2, 2008, Calendar.OCTOBER, 8);
        assertDate(e3, 2008, Calendar.SEPTEMBER, 30);
        assertDate(e4, 2008, Calendar.SEPTEMBER, 30);
    }