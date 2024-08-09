private boolean strong(RexNode node) {
    switch (node.getKind()) {
    case LITERAL:
      return ((RexLiteral) node).getValue() == null;
    case IS_TRUE:
    case IS_NOT_NULL:
    case AND:
    case EQUALS:
    case NOT_EQUALS:
    case LESS_THAN:
    case LESS_THAN_OR_EQUAL:
    case GREATER_THAN:
    case GREATER_THAN_OR_EQUAL:
      return anyStrong(((RexCall) node).getOperands());
    case OR:
      return allStrong(((RexCall) node).getOperands());
    case INPUT_REF:
      return nullColumns.get(((RexInputRef) node).getIndex());
    default:
      return false;
    }
  }