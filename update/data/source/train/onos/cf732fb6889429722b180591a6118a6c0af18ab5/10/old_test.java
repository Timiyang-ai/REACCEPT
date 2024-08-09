@Test
    public void matchSctpDstTest() {
        Criterion criterion = Criteria.matchSctpDst(40000);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result, matchesCriterion(criterion));
    }