@Test
    public void matchSctpDstTest() {
        Criterion criterion = Criteria.matchSctpDst(tpPort);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result, matchesCriterion(criterion));
    }