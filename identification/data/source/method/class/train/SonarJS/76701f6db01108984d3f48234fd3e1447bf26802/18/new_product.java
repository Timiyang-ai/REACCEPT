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
      case CONDITIONAL_AND:
      case CONDITIONAL_OR:
      case CONDITIONAL_EXPRESSION:
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
        NewExpressionTree newExpressionTree = (NewExpressionTree) expression;
        int arguments = newExpressionTree.arguments() == null ? 0 : newExpressionTree.arguments().parameters().size();
        pop(newStack, arguments + 1);
        newStack.push(new SymbolicValueWithConstraint(Constraint.OBJECT));
        break;
      case DOT_MEMBER_EXPRESSION:
      case SPREAD_ELEMENT:
      case VOID:
      case AWAIT:
        pop(newStack, 1);
        pushUnknown(newStack);
        break;
      case DELETE:
        pop(newStack, 1);
        newStack.push(new SymbolicValueWithConstraint(Constraint.BOOLEAN));
        break;
      case YIELD_EXPRESSION:
        if (((YieldExpressionTree) expression).argument() != null) {
          pop(newStack, 1);
        }
        pushUnknown(newStack);
        break;
      case POSTFIX_DECREMENT:
      case POSTFIX_INCREMENT:
      case PREFIX_DECREMENT:
      case PREFIX_INCREMENT:
      case UNARY_MINUS:
      case UNARY_PLUS:
      case BITWISE_COMPLEMENT:
        pop(newStack, 1);
        newStack.push(new SymbolicValueWithConstraint(Constraint.NUMBER));
        break;
      case CALL_EXPRESSION:
        pop(newStack, ((CallExpressionTree) expression).arguments().parameters().size() + 1);
        pushUnknown(newStack);
        break;
      case FUNCTION_EXPRESSION:
      case GENERATOR_FUNCTION_EXPRESSION:
      case ARROW_FUNCTION:
        newStack.push(new SymbolicValueWithConstraint(Constraint.FUNCTION));
        break;
      case REGULAR_EXPRESSION_LITERAL:
      case THIS:
      case SUPER:
      case NEW_TARGET:
      case JSX_SELF_CLOSING_ELEMENT:
      case JSX_STANDARD_ELEMENT:
        pushUnknown(newStack);
        break;
      case CLASS_EXPRESSION:
        newStack.push(new SymbolicValueWithConstraint(Constraint.OTHER_OBJECT));
        break;
      case OBJECT_LITERAL:
        popObjectLiteralProperties((ObjectLiteralTree)expression, newStack);
        newStack.push(new SymbolicValueWithConstraint(Constraint.OTHER_OBJECT));
        break;
      case ARRAY_LITERAL:
        pop(newStack, ((ArrayLiteralTree) expression).elements().size());
        newStack.push(new SymbolicValueWithConstraint(Constraint.ARRAY));
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
      case EXPONENT:
      case RELATIONAL_IN:
      case INSTANCE_OF:
        pop(newStack, 2);
        pushUnknown(newStack);
        break;
      case LESS_THAN:
      case GREATER_THAN:
      case LESS_THAN_OR_EQUAL_TO:
      case GREATER_THAN_OR_EQUAL_TO:
        pop(newStack, 2);
        newStack.push(new SymbolicValueWithConstraint(Constraint.BOOLEAN));
        break;
      case PLUS:
        newStack.push(new PlusSymbolicValue(newStack.pop(), newStack.pop()));
        break;
      case MINUS:
      case DIVIDE:
      case REMAINDER:
      case MULTIPLY:
      case BITWISE_AND:
      case BITWISE_XOR:
      case BITWISE_OR:
      case LEFT_SHIFT:
      case RIGHT_SHIFT:
      case UNSIGNED_RIGHT_SHIFT:
        pop(newStack, 2);
        newStack.push(new SymbolicValueWithConstraint(Constraint.NUMBER));
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