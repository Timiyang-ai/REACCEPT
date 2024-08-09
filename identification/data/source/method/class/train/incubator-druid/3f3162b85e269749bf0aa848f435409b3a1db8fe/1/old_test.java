  @Test
  public void deserializeTest() throws IOException
  {
    final DefaultObjectMapper mapper = new DefaultObjectMapper();
    final ResponseContext ctx = ResponseContext.deserialize(
        mapper.writeValueAsString(
            ImmutableMap.of(
                "ETag", "string-value",
                "count", 100L,
                "cpuConsumed", 100000L
            )
        ),
        mapper
    );
    Assert.assertEquals("string-value", ctx.get(ResponseContext.Key.ETAG));
    Assert.assertEquals(100, ctx.get(ResponseContext.Key.NUM_SCANNED_ROWS));
    Assert.assertEquals(100000, ctx.get(ResponseContext.Key.CPU_CONSUMED_NANOS));
    ctx.add(ResponseContext.Key.NUM_SCANNED_ROWS, 10L);
    Assert.assertEquals(110L, ctx.get(ResponseContext.Key.NUM_SCANNED_ROWS));
    ctx.add(ResponseContext.Key.CPU_CONSUMED_NANOS, 100);
    Assert.assertEquals(100100L, ctx.get(ResponseContext.Key.CPU_CONSUMED_NANOS));
  }