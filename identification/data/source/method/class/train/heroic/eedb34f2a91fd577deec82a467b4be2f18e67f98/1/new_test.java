    @Test
    public void throwUnlessEmpty() {
        final AggregationArguments arguments =
            new AggregationArguments(ImmutableList.of(), ImmutableMap.of());

        arguments.throwUnlessEmpty("foo");
    }