@Test
    public void matchSctpDstTest() {
        Criterion criterion = Criteria.matchSctpDst((short) 40000);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("sctpPort").asInt(), is(40000));
    }