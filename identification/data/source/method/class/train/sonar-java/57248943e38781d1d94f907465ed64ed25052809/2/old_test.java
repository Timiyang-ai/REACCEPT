  @Test
  public void test_flow_messages_on_parameter_declaration() throws Exception {
    JavaCheckVerifier.verify("src/test/files/se/FlowMessagesParameterDeclaration.java", new NullDereferenceCheck(), new ConditionalUnreachableCodeCheck(),
      new BooleanGratuitousExpressionsCheck());
  }