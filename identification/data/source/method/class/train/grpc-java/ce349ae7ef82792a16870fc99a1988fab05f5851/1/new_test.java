  @Test
  public void toBuilderTest() {
    MethodDescriptor<String, String> md1 = MethodDescriptor.<String, String>newBuilder()
        .setType(MethodType.UNARY)
        .setFullMethodName("package.service/method")
        .setRequestMarshaller(StringMarshaller.INSTANCE)
        .setResponseMarshaller(StringMarshaller.INSTANCE)
        .setSampledToLocalTracing(true)
        .setIdempotent(true)
        .setSafe(true)
        .setSchemaDescriptor(new Object())
        .build();
    // Verify that we are not using any default builder values, so if md1 and md2 matches,
    // it's because toBuilder explicitly copied it.
    MethodDescriptor<String, String> defaults = MethodDescriptor.<String, String>newBuilder()
        .setType(MethodType.UNARY)
        .setFullMethodName("package.service/method")
        .setRequestMarshaller(StringMarshaller.INSTANCE)
        .setResponseMarshaller(StringMarshaller.INSTANCE)
        .build();
    assertNotEquals(md1.isSampledToLocalTracing(), defaults.isSampledToLocalTracing());
    assertNotEquals(md1.isIdempotent(), defaults.isIdempotent());
    assertNotEquals(md1.isSafe(), defaults.isSafe());
    assertNotEquals(md1.getSchemaDescriptor(), defaults.getSchemaDescriptor());

    // Verify that the builder correctly copied over the values
    MethodDescriptor<Integer, Integer> md2 = md1.toBuilder(
        IntegerMarshaller.INSTANCE,
        IntegerMarshaller.INSTANCE).build();
    assertSame(md1.getType(), md2.getType());
    assertSame(md1.getFullMethodName(), md2.getFullMethodName());
    assertSame(IntegerMarshaller.INSTANCE, md2.getRequestMarshaller());
    assertSame(IntegerMarshaller.INSTANCE, md2.getResponseMarshaller());
    assertEquals(md1.isSampledToLocalTracing(), md2.isSampledToLocalTracing());
    assertEquals(md1.isIdempotent(), md2.isIdempotent());
    assertEquals(md1.isSafe(), md2.isSafe());
    assertSame(md1.getSchemaDescriptor(), md2.getSchemaDescriptor());
  }