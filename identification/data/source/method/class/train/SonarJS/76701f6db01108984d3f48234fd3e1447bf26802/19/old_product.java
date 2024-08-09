public ExpressionStack execute(ExpressionTree expression, ProgramStateConstraints constraints) {
    Deque<SymbolicValue> newStack = copy();
    Kind kind = ((JavaScriptTree) expression).getKind();
    switch (kind) {
      case LOGICAL_COMPLEMENT:
        SymbolicValue negatedValue = newStack.pop();
        newStack.push(LogicalNotSymbolicValue.create(negatedValue));
        break;
      case TYPEOF:
        newStack.push(new TypeOfSymbolicValue(newStack.pop()));
        break;
      case NEW_EXPRESSION:
        executeNewExpression((NewExpressionTree) expression, newStack);
        break;
      case SPREAD_ELEMENT:
      case VOID:
      case AWAIT:
        pop(newStack, 1);
        pushUnknown(newStack);
        break;
      case DELETE:
        pop(newStack, 1);
        newStack.push(new SymbolicValueWithConstraint(Constraint.BOOLEAN_PRIMITIVE));
        break;
      case YIELD_EXPRESSION:
        if (((YieldExpressionTree) expression).argument() != null) {
          pop(newStack, 1);
        }
        pushUnknown(newStack);
        break;
      case BITWISE_COMPLEMENT:
        pop(newStack, 1);
        newStack.push(new SymbolicValueWithConstraint(Constraint.NUMBER_PRIMITIVE));
        break;
      case CALL_EXPRESSION:
        executeCallExpression((CallExpressionTree) expression, newStack, constraints);
        break;
      case FUNCTION_EXPRESSION:
      case GENERATOR_FUNCTION_EXPRESSION:
      case ARROW_FUNCTION:
        newStack.push(new FunctionWithTreeSymbolicValue((FunctionTree) expression));
        break;
      case THIS:
      case SUPER:
      case IMPORT:
      case NEW_TARGET:
      case JSX_SELF_CLOSING_ELEMENT:
      case JSX_STANDARD_ELEMENT:
      case FLOW_CASTING_EXPRESSION:
        pushUnknown(newStack);
        break;
      case CLASS_EXPRESSION:
        newStack.push(new SymbolicValueWithConstraint(Constraint.OTHER_OBJECT));
        break;
      case EXPONENT_ASSIGNMENT:
      case LEFT_SHIFT_ASSIGNMENT:
      case RIGHT_SHIFT_ASSIGNMENT:
      case UNSIGNED_RIGHT_SHIFT_ASSIGNMENT:
      case AND_ASSIGNMENT:
      case XOR_ASSIGNMENT:
      case OR_ASSIGNMENT:
      case TAGGED_TEMPLATE:
      case EXPONENT:
        pop(newStack, 2);
        pushUnknown(newStack);
        break;
      case RELATIONAL_IN:
        pop(newStack, 2);
        newStack.push(new SymbolicValueWithConstraint(Constraint.BOOLEAN_PRIMITIVE));
        break;
      case INSTANCE_OF:
        SymbolicValue constructorValue = newStack.pop();
        SymbolicValue objectValue = newStack.pop();
        newStack.push(new InstanceOfSymbolicValue(objectValue, constructorValue));
        break;
      case EQUAL_TO:
      case NOT_EQUAL_TO:
      case STRICT_EQUAL_TO:
      case STRICT_NOT_EQUAL_TO:
      case LESS_THAN:
      case GREATER_THAN:
      case LESS_THAN_OR_EQUAL_TO:
      case GREATER_THAN_OR_EQUAL_TO:
        SymbolicValue rightOperand = newStack.pop();
        SymbolicValue leftOperand = newStack.pop();
        newStack.push(RelationalSymbolicValue.create(kind, leftOperand, rightOperand));
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
      case ARRAY_ASSIGNMENT_PATTERN:
      case OBJECT_ASSIGNMENT_PATTERN:
        newStack.push(UnknownSymbolicValue.UNKNOWN);
        break;
      case PLUS_ASSIGNMENT:
      case PLUS:
      case MINUS:
      case DIVIDE:
      case REMAINDER:
      case MULTIPLY:
      case MULTIPLY_ASSIGNMENT:
      case DIVIDE_ASSIGNMENT:
      case REMAINDER_ASSIGNMENT:
      case MINUS_ASSIGNMENT:
      case BITWISE_AND:
      case BITWISE_XOR:
      case BITWISE_OR:
      case LEFT_SHIFT:
      case RIGHT_SHIFT:
      case UNSIGNED_RIGHT_SHIFT:

      case POSTFIX_DECREMENT:
      case POSTFIX_INCREMENT:
      case PREFIX_DECREMENT:
      case PREFIX_INCREMENT:
      case UNARY_MINUS:
      case UNARY_PLUS:

      case PROPERTY_IDENTIFIER:
      case BINDING_IDENTIFIER:
      case CONDITIONAL_AND:
      case CONDITIONAL_OR:
      case CONDITIONAL_EXPRESSION:

      case IDENTIFIER_REFERENCE:

      case NULL_LITERAL:
      case NUMERIC_LITERAL:
      case STRING_LITERAL:
      case BOOLEAN_LITERAL:
      case REGULAR_EXPRESSION_LITERAL:
      case OBJECT_LITERAL:
      case ARRAY_LITERAL:
      case TEMPLATE_LITERAL:
      case BRACKET_MEMBER_EXPRESSION:
      case DOT_MEMBER_EXPRESSION:
        break;
      default:
        throw new IllegalArgumentException("Unexpected kind of expression to execute: " + kind);
    }
    return new ExpressionStack(newStack);
  }