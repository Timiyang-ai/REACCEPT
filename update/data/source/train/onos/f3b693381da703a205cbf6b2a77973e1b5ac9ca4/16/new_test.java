@Test
    public void matchIcmpv6CodeTest() {
        Criterion criterion = Criteria.matchIcmpv6Code((byte) 250);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("icmpv6Code").asInt(), is(250));
    }