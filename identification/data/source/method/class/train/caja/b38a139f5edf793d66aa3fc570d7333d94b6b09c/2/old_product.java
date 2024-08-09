public void addFact(Expression e, Fact fact) {
    // If we know it is a boolean, we can upgrade from LIKE to IS.
    if (fact.type == Fact.Type.LIKE && "boolean".equals(e.typeOf())) {
      fact = fact.isTruthy() ? Fact.TRUE : Fact.FALSE;
    }

    String digest = nodeDigest(e);
    Fact oldFact = facts.get(digest);
    if (oldFact != null && !oldFact.isLessSpecificThan(fact)) { return; }
    putFact(e, digest, fact);

    // Infer extra facts
    if (e instanceof Operation) {
      Operation op = ((Operation) e);
      List<? extends Expression> operands = op.children();
      switch (op.getOperator()) {
        case NOT:
          // If (!x) then x is falsey.
          addFuzzyFact(operands.get(0), !fact.isTruthy());
          break;
        case LESS_THAN:
        case GREATER_THAN:
          if (fact.isTruthy()) {
            // If (a < b) the a != b, and !(a > b).
            Expression left = operands.get(0);
            Expression right = operands.get(1);
            Operator inclusive = op.getOperator() == Operator.LESS_THAN
                ? Operator.LESS_EQUALS : Operator.GREATER_EQUALS;
            addFact(Operation.create(UNK, inclusive, left, right), Fact.TRUE);
            addFact(
                Operation.create(UNK, Operator.STRICTLY_NOT_EQUAL, left, right),
                Fact.TRUE);
            // Incomparable values like NaN means we can conclude nothing if
            // !(a < b).
          }
          break;
        case LESS_EQUALS:
        case GREATER_EQUALS:
          if (fact.isFalsey()) {
            // if !(a <= b) then a !== b.
            Expression left = operands.get(0);
            Expression right = operands.get(1);
            addFact(
                Operation.create(UNK, Operator.STRICTLY_NOT_EQUAL, left, right),
                Fact.TRUE);
          }
          break;
        case INSTANCE_OF:
          // if (x instanceof y) does not throw, then y must be a function.
          // if it's true, then x must be an object.
          // Note: primitives are not instances of their wrapper class.
          addFact(
              Operation.create(UNK, Operator.TYPEOF, operands.get(1)),
              Fact.is(StringLiteral.valueOf(UNK, "function")));
          if (fact.isTruthy()) {
            addFact(operands.get(0), Fact.TRUTHY);
          }
          break;
        case EQUAL:
          addFact(
              Operation.create(
                  UNK, Operator.NOT_EQUAL, operands.get(0), operands.get(1)),
              fact.isTruthy() ? Fact.FALSE : Fact.TRUE);
          break;
        case NOT_EQUAL:
          addFact(
              Operation.create(
                  UNK, Operator.EQUAL, operands.get(0), operands.get(1)),
              fact.isTruthy() ? Fact.FALSE : Fact.TRUE);
          break;
        case STRICTLY_EQUAL:
          if (fact.isTruthy()) {
            Expression lhs = operands.get(0);
            Expression rhs = operands.get(1);
            addFact(Operation.create(UNK, Operator.EQUAL, lhs, rhs), Fact.TRUE);
            if (rhs instanceof Literal) {
              addFact(lhs, Fact.is((Literal) rhs));
            } else {
              String typeOf = rhs.typeOf();
              if (typeOf != null && lhs.typeOf() == null) {
                addFact(
                    Operation.create(UNK, Operator.TYPEOF, lhs),
                    Fact.is(StringLiteral.valueOf(UNK, typeOf)));
              }
              Boolean truthiness = rhs.conditionResult();
              if (truthiness != null) {
                addFuzzyFact(lhs, truthiness);
              }
            }
          }
          addFact(
              Operation.create(
                  UNK, Operator.STRICTLY_NOT_EQUAL,
                  operands.get(0), operands.get(1)),
              fact.isTruthy() ? Fact.FALSE : Fact.TRUE);
          break;
        case STRICTLY_NOT_EQUAL:
          addFact(Operation.create(
              UNK, Operator.STRICTLY_EQUAL, operands.get(0), operands.get(1)),
              fact.isTruthy() ? Fact.FALSE : Fact.TRUE);
          break;
        case LOGICAL_AND:
        case LOGICAL_OR:
          boolean isAnd = op.getOperator() == Operator.LOGICAL_AND;
          if (fact.isTruthy() == isAnd) {
            addFuzzyFact(operands.get(0), isAnd);
            addFact(operands.get(1), fact);  // Second value is result
          }
          break;
        case TYPEOF:
          if (fact.type == Fact.Type.IS
              && fact.value instanceof StringLiteral) {
            String s = ((StringLiteral) fact.value).getUnquotedValue();
            Expression op0 = operands.get(0);
            if ("undefined".equals(s)) {
              addFact(op0, Fact.UNDEFINED);
            } else {
              if ("function".equals(s)) { addFact(op0, Fact.TRUTHY); }
              // undefined is a commonly tested value, so infer its absence
              // for other types.
              addFact(
                  Operation.create(
                      UNK, Operator.STRICTLY_EQUAL, op0, Fact.UNDEFINED.value),
                  Fact.FALSE);
            }
          }
          break;
        default:
          break;
      }
      // (a < b) -> (b > a) since we know (a) and (b) are pure
      if (operands.size() == 2) {
        Operator swapped = WITH_REVERSE_ORDER.get(op.getOperator());
        if (swapped != null) {
          addFact(
              Operation.create(
                  op.getFilePosition(), swapped,
                  operands.get(1), operands.get(0)),
              fact);
        }
      }
    }
  }