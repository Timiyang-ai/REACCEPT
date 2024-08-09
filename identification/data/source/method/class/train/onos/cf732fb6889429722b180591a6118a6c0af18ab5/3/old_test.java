@Test
    public void matchTcpDstTest() {
        Criterion criterion = Criteria.matchTcpDst((short) 40000);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result, matchesCriterion(criterion));
    }