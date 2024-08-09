@Test
    public void matchEthTypeTest() {
        Criterion criterion = Criteria.matchEthType((short) 0x8844);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("ethType").asInt(), is(0x8844));
    }