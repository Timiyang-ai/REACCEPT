@Test
    public void matchLambdaTest() {
        Criterion criterion = Criteria.matchLambda((short) 40000);
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result.get("type").textValue(), is(criterion.type().toString()));
        assertThat(result.get("lambda").asInt(), is(40000));
    }