@Test
    public void matchTcpDstTest() {
        Criterion criterion = Criteria.matchTcpDst(tpPort);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result, matchesCriterion(criterion));
    }