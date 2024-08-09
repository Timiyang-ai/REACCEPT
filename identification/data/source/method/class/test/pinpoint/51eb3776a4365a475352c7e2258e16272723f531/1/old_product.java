public MatcherOperand and(MatcherOperand operand) {
        final AndMatcherOperator operator = new AndMatcherOperator();
        operator.setLeftOperand(this);
        operator.setRightOperand(operand);
        return operator;
    }