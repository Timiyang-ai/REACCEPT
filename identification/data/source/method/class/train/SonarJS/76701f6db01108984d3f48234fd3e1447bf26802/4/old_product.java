public ExpressionStack execute(ExpressionTree expression) {
    Deque<SymbolicValue> newStack = copy();
    Kind kind = ((JavaScriptTree) expression).getKind();
    switch (kind) {
      case IDENTIFIER_REFERENCE:
        if (SymbolicExecution.isUndefined((IdentifierTree) expression)) {
          newStack.push(SpecialSymbolicValue.UNDEFINED);
          break;
        }
        throw new IllegalArgumentException("Unexpected kind of expression to execute: " + kind);
      case IDENTIFIER_NAME:
      case BINDING_IDENTIFIER:
        break;
      case NULL_LITERAL:
        newStack.push(SpecialSymbolicValue.NULL);
        break;
      case NUMERIC_LITERAL:
      case STRING_LITERAL:
      case BOOLEAN_LITERAL:
        newStack.push(LiteralSymbolicValue.get((LiteralTree) expression));
        break;
      case LOGICAL_COMPLEMENT:
        SymbolicValue negatedValue = newStack.pop();
        newStack.push(LogicalNotSymbolicValue.create(negatedValue));
        break;
      case EQUAL_TO:
        newStack.push(EqualToSymbolicValue.createEqual(newStack.pop(), newStack.pop()));
        break;
      case NOT_EQUAL_TO:
        newStack.push(EqualToSymbolicValue.createNotEqual(newStack.pop(), newStack.pop()));
        break;
      case STRICT_EQUAL_TO:
        newStack.push(EqualToSymbolicValue.createStrictEqual(newStack.pop(), newStack.pop()));
        break;
      case STRICT_NOT_EQUAL_TO:
        newStack.push(EqualToSymbolicValue.createStrictNotEqual(newStack.pop(), newStack.pop()));
        break;
      case TYPEOF:
        newStack.push(new TypeOfSymbolicValue(newStack.pop()));
        break;
      case NEW_EXPRESSION:
      case DOT_MEMBER_EXPRESSION:
      case SPREAD_ELEMENT:
      case YIELD_EXPRESSION:
      case POSTFIX_DECREMENT:
      case POSTFIX_INCREMENT:
      case PREFIX_DECREMENT:
      case PREFIX_INCREMENT:
      case UNARY_MINUS:
      case UNARY_PLUS:
      case BITWISE_COMPLEMENT:
      case DELETE:
      case VOID:
        pop(newStack, 1);
        pushUnknown(newStack);
        break;
      case CALL_EXPRESSION:
        pop(newStack, ((CallExpressionTree) expression).arguments().parameters().size() + 1);
        pushUnknown(newStack);
        break;
      case CONDITIONAL_EXPRESSION:
        SymbolicValue result = newStack.pop();
        newStack.pop();
        newStack.push(result);
        break;
      case REGULAR_EXPRESSION_LITERAL:
      case FUNCTION_EXPRESSION:
      case GENERATOR_FUNCTION_EXPRESSION:
      case THIS:
      case SUPER:
      case ARROW_FUNCTION:
      case OBJECT_LITERAL:
      case JSX_SELF_CLOSING_ELEMENT:
      case JSX_STANDARD_ELEMENT:
        pushUnknown(newStack);
        break;
      case ARRAY_LITERAL:
        pop(newStack, ((ArrayLiteralTree) expression).elements().size());
        pushUnknown(newStack);
        break;
      case TEMPLATE_LITERAL:
        pop(newStack, ((TemplateLiteralTree) expression).expressions().size());
        pushUnknown(newStack);
        break;
      case EXPONENT_ASSIGNMENT:
      case MULTIPLY_ASSIGNMENT:
      case DIVIDE_ASSIGNMENT:
      case REMAINDER_ASSIGNMENT:
      case PLUS_ASSIGNMENT:
      case MINUS_ASSIGNMENT:
      case LEFT_SHIFT_ASSIGNMENT:
      case RIGHT_SHIFT_ASSIGNMENT:
      case UNSIGNED_RIGHT_SHIFT_ASSIGNMENT:
      case AND_ASSIGNMENT:
      case XOR_ASSIGNMENT:
      case OR_ASSIGNMENT:
      case BRACKET_MEMBER_EXPRESSION:
      case TAGGED_TEMPLATE:
      case MULTIPLY:
      case EXPONENT:
      case DIVIDE:
      case REMAINDER:
      case PLUS:
      case MINUS:
      case LEFT_SHIFT:
      case RIGHT_SHIFT:
      case UNSIGNED_RIGHT_SHIFT:
      case RELATIONAL_IN:
      case INSTANCE_OF:
      case LESS_THAN:
      case GREATER_THAN:
      case LESS_THAN_OR_EQUAL_TO:
      case GREATER_THAN_OR_EQUAL_TO:
      case BITWISE_AND:
      case BITWISE_XOR:
      case BITWISE_OR:
        pop(newStack, 2);
        pushUnknown(newStack);
        break;
      case COMMA_OPERATOR:
        SymbolicValue commaResult = newStack.pop();
        newStack.pop();
        newStack.push(commaResult);
        break;
      case ASSIGNMENT:
        SymbolicValue assignedValue = newStack.pop();
        newStack.pop();
        newStack.push(assignedValue);
        break;
      default:
        throw new IllegalArgumentException("Unexpected kind of expression to execute: " + kind);
    }
    return new ExpressionStack(newStack);
  }