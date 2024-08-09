@Test
    public void matchUdpDstTest() {
        Criterion criterion = Criteria.matchUdpDst(40000);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result, matchesCriterion(criterion));
    }