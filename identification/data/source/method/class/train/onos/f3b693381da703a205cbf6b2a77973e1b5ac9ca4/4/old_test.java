@Test
    public void matchIPDscpTest() {
        Criterion criterion = Criteria.matchIPDscp((byte) 5);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("ipDscp").asInt(), is(5));
    }