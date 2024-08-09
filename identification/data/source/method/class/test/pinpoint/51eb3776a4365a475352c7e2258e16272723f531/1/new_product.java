public MatcherOperand and(MatcherOperand operand) {
        return new AndMatcherOperator(this, operand);
    }