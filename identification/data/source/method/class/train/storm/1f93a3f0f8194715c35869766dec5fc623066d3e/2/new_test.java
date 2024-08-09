    @Test
    public void updateState() throws Exception {
        List<TridentTuple> tuples = tuples(index, type, documentId, source);
        state.updateState(tuples);
    }