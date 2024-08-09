public boolean isNull(RexNode node) {
    switch (node.getKind()) {
    case LITERAL:
      return ((RexLiteral) node).getValue() == null;
    case IS_TRUE:
    case IS_NOT_NULL:
    case AND:
    case NOT:
    case EQUALS:
    case NOT_EQUALS:
    case LESS_THAN:
    case LESS_THAN_OR_EQUAL:
    case GREATER_THAN:
    case GREATER_THAN_OR_EQUAL:
    case PLUS_PREFIX:
    case MINUS_PREFIX:
    case PLUS:
    case TIMESTAMP_ADD:
    case MINUS:
    case TIMESTAMP_DIFF:
    case TIMES:
    case DIVIDE:
    case CAST:
    case REINTERPRET:
    case TRIM:
    case LTRIM:
    case RTRIM:
    case CEIL:
    case FLOOR:
    case EXTRACT:
    case GREATEST:
    case LEAST:
      return anyNull(((RexCall) node).getOperands());
    case OR:
      return allNull(((RexCall) node).getOperands());
    case INPUT_REF:
      return isNull((RexInputRef) node);
    default:
      return false;
    }
  }