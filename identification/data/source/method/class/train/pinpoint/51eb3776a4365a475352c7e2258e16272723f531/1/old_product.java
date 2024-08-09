public MatcherOperand not() {
        final NotMatcherOperator operator = new NotMatcherOperator();
        operator.setRightOperand(this);
        return operator;
    }