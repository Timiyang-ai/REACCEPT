@Test
    public void matchVlanPcpTest() {
        Criterion criterion = Criteria.matchVlanPcp((byte) 7);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("priority").asInt(), is(7));
    }