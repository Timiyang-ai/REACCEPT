@Test
    public void testInAbbrevJournalNames_case1() throws Exception {
        String input = "Nature";
        List<OffsetPosition> journalsPositions = target.inAbbrevJournalNames(input);

        assertNotNull(journalsPositions);
        assertThat(journalsPositions, hasSize(1));
        assertThat(journalsPositions.get(0).start, is(0));
    }