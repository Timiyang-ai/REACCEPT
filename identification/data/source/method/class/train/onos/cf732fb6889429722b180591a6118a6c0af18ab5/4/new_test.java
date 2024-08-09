@Test
    public void matchTcpSrcTest() {
        Criterion criterion = Criteria.matchTcpSrc(tpPort);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result, matchesCriterion(criterion));
    }