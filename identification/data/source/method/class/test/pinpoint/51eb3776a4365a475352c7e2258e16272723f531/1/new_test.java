    @Test
    public void and() {
        MatcherOperand operand = new ClassInternalNameMatcherOperand("java/lang/String");
        operand = operand.and(new InterfaceInternalNameMatcherOperand("java/lang/Runnable", false));
        operand = operand.and(new AnnotationInternalNameMatcherOperand("javax/annotation/Resource", false));

        assertTrue(operand instanceof AndMatcherOperator);
        AndMatcherOperator operator = (AndMatcherOperator) operand;
        assertTrue(operator.getLeftOperand() instanceof AndMatcherOperator);
        assertTrue(operator.getRightOperand() instanceof AnnotationInternalNameMatcherOperand);

        operator = (AndMatcherOperator) operator.getLeftOperand();
        assertTrue(operator.getLeftOperand() instanceof ClassInternalNameMatcherOperand);
        assertTrue(operator.getRightOperand() instanceof InterfaceInternalNameMatcherOperand);
    }