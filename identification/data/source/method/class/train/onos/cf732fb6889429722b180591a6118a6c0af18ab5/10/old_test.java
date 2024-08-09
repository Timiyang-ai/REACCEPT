@Test
    public void matchSctpSrcTest() {
        Criterion criterion = Criteria.matchSctpSrc(40000);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result, matchesCriterion(criterion));
    }