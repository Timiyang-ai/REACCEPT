@Test
    public void matchUdpSrcTest() {
        Criterion criterion = Criteria.matchUdpSrc((short) 22);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("udpPort").asInt(), is(22));
    }