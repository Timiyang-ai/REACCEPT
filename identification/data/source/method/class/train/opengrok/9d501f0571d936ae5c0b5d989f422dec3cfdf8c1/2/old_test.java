@Test
    public void parseFileLog() throws Exception {
        String output = "//Path/To/Folder/In/Workspace/Filename\n" +
                "\n" +
                "... #4 change 1234 edit on 2008/08/19 by User@UserWorkspaceName (text)\n" +
                "\n" +
                "        Comment for the change number 4\n" +
                "\n" +
                "... #3 change 5678 edit on 2008/08/19 by ADMIN@UserWorkspaceName (text)\n" +
                "\n" +
                "        Comment for the change\n" +
                "\n" +
                "... #2 change 8765 edit on 2008/08/01 by ADMIN@UserWorkspaceName (text)\n" +
                "\n" +
                "        Comment for the change\n" +
                "\n" +
                "... #1 change 1 add on 2008/07/30 by ADMIN@UserWorkspaceName (text)\n" +
                "\n" +
                "        Comment for the change";

        History result = PerforceHistoryParser.parseFileLog(new StringReader(output));

        assertNotNull(result);
        assertEquals(4, result.getHistoryEntries().size());

        HistoryEntry e1 = result.getHistoryEntries().get(0);
        assertEquals("4", e1.getRevision());
        assertEquals("User", e1.getAuthor());
        assertEquals(0, e1.getFiles().size());
        assertTrue(e1.getMessage().contains("number 4"));

        HistoryEntry e4 = result.getHistoryEntries().get(3);
        assertEquals("1", e4.getRevision());
    }