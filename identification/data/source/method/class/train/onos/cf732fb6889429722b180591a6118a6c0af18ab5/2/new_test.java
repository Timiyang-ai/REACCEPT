@Test
    public void matchSctpSrcTest() {
        Criterion criterion = Criteria.matchSctpSrc(tpPort);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result, matchesCriterion(criterion));
    }