@Test
    public void matchIPProtocolTest() {
        Criterion criterion = Criteria.matchIPProtocol((byte) 7);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("protocol").asInt(), is(7));
    }