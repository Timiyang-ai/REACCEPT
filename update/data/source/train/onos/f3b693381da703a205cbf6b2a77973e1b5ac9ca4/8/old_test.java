@Test
    public void matchIcmpv6TypeTest() {
        Criterion criterion = Criteria.matchIcmpv6Type((byte) 15);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("icmpv6Type").asInt(), is(15));
    }