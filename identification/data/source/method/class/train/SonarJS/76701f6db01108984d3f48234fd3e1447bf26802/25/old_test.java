  private void execute(String expressionSource) {
    stack = ExpressionStack.emptyStack();
    ScriptTree script = (ScriptTree) parser.parse(expressionSource);

    ControlFlowGraph cfg = ControlFlowGraph.build(script);
    for (Tree element : cfg.start().elements()) {

      if (element.is(Kind.IDENTIFIER_REFERENCE)) {
        pushValues(simple1);

      } else if (element.is(KindSet.LITERAL_KINDS)) {
        programState = new LiteralProgramPoint(element).execute(programState.withStack(stack)).get();
        stack = programState.getStack();

      } else if (element instanceof ExpressionTree) {
        stack = stack.execute((ExpressionTree) element, ProgramState.emptyState());
      }
    }

  }