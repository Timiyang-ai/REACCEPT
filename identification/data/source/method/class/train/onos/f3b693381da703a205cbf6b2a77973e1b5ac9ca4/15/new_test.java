@Test
    public void matchIcmpTypeTest() {
        Criterion criterion = Criteria.matchIcmpType((byte) 250);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("icmpType").asInt(), is(250));
    }