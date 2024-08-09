@Test
    public void matchUdpSrcTest() {
        Criterion criterion = Criteria.matchUdpSrc(40000);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result, matchesCriterion(criterion));
    }