@Test
    public void matchTcpSrcTest() {
        Criterion criterion = Criteria.matchTcpSrc((short) 40000);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result, matchesCriterion(criterion));
    }