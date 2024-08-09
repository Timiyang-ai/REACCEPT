    @Test
    public void not() {
        MatcherOperand operand = new ClassInternalNameMatcherOperand("java/lang/String");
        operand = operand.not();

        assertTrue(operand instanceof NotMatcherOperator);
        NotMatcherOperator operator = (NotMatcherOperator) operand;
        assertTrue(operator.getRightOperand() instanceof ClassInternalNameMatcherOperand);
    }