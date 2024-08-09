    @Test
    public void getHunks() throws IOException {
        // given
        FileDiff fileDiff = new FileDiff();
        fileDiff.a = new RawText("apple\nbanana\ncat\n".getBytes());
        fileDiff.b = new RawText("apple\nbanana\ncorn\n".getBytes());
        DiffAlgorithm diffAlgorithm =
                DiffAlgorithm.getAlgorithm(DiffAlgorithm.SupportedAlgorithm.HISTOGRAM);
        fileDiff.editList = diffAlgorithm.diff(RawTextComparator.DEFAULT, fileDiff.a,
                        fileDiff.b);

        // when
        List<Hunk> hunks = fileDiff.getHunks();

        // then
        Hunk expectedHunk = new Hunk();
        expectedHunk.beginA = 0;
        expectedHunk.endA = 3;
        expectedHunk.beginB = 0;
        expectedHunk.endB = 3;
        expectedHunk.lines = new ArrayList<>();
        expectedHunk.lines.add(new DiffLine(fileDiff, DiffLineType.CONTEXT, 0, 0, "apple"));
        expectedHunk.lines.add(new DiffLine(fileDiff, DiffLineType.CONTEXT, 1, 1, "banana"));
        expectedHunk.lines.add(new DiffLine(fileDiff, DiffLineType.REMOVE, 2, null, "cat"));
        expectedHunk.lines.add(new DiffLine(fileDiff, DiffLineType.ADD, null, 2, "corn"));
        ArrayList<Hunk> expectedHunks = new ArrayList<>();
        expectedHunks.add(expectedHunk);
        assertThat(hunks).describedAs("Test FileDiff.hunks").isEqualTo(expectedHunks);
    }