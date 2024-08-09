@Test
    public void matchOpticalSignalTypeTest() {
        Criterion criterion = Criteria.matchOpticalSignalType((short) 40000);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("signalType").asInt(), is(40000));
    }