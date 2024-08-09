@Test
    public void matchTcpSrcTest() {
        Criterion criterion = Criteria.matchTcpSrc((short) 40000);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("tcpPort").asInt(), is(40000));
    }