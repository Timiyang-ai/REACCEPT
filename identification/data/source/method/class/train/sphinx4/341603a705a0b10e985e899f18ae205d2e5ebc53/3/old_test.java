    private void align(List<String> database, List<String> query,
            Integer... result) {
        LongTextAligner aligner = new LongTextAligner(database, 1);
        int[] alignment = aligner.align(query);

        assertThat(Utilities.asList(alignment), contains(result));
    }