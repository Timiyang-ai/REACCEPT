@Test
    public void matchUdpSrcTest() {
        Criterion criterion = Criteria.matchUdpSrc(tpPort);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result, matchesCriterion(criterion));
    }