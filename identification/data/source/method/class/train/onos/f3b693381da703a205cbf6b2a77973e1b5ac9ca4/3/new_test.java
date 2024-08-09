@Test
    public void matchIPEcnTest() {
        Criterion criterion = Criteria.matchIPEcn((byte) 7);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("ipEcn").asInt(), is(7));
    }