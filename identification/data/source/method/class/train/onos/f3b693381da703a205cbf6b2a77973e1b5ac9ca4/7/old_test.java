@Test
    public void matchIcmpCodeTest() {
        Criterion criterion = Criteria.matchIcmpCode((byte) 6);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("icmpCode").asInt(), is(6));
    }