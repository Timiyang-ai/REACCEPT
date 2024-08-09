@Test
    public void matchIPv6FlowLabelTest() {
        Criterion criterion = Criteria.matchIPv6FlowLabel(0xffffe);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("flowLabel").asInt(), is(0xffffe));
    }