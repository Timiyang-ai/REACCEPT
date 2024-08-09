@Test
    public void matchTcpDstTest() {
        Criterion criterion = Criteria.matchTcpDst((short) 40000);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("tcpPort").asInt(), is(40000));
    }