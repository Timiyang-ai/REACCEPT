@Test
    public void matchSctpSrcTest() {
        Criterion criterion = Criteria.matchSctpSrc((short) 40000);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("sctpPort").asInt(), is(40000));
    }