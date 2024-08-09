@Test
    public void matchUdpDstTest() {
        Criterion criterion = Criteria.matchUdpDst(tpPort);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result, matchesCriterion(criterion));
    }