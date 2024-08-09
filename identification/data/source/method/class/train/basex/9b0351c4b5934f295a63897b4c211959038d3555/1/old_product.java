protected final Expr count(final Op o) throws QueryException {
    // evaluate argument
    final Expr a = expr[1];
    if(!a.item()) return this;
    final Item it = (Item) a;
    if(!it.num() && !it.unt()) return this;

    final double v = it.dbl(input);
    // TRUE: c > (v<0), c != (v<0), c >= (v<=0), c != not-int(v)
    if((o == Op.GT || o == Op.NE) && v < 0 || o == Op.GE && v <= 0 ||
       o == Op.NE && v != (int) v) return Bln.TRUE;
    // FALSE: c < (v<=0), c <= (v<0), c = (v<0), c = not-int(v)
    if(o == Op.LT && v <= 0 || (o == Op.LE || o == Op.EQ) && v < 0 ||
       o == Op.EQ && v != (int) v) return Bln.FALSE;
    // EXISTS: c > (v<1), c >= (v<=1), c != (v=0)
    if(o == Op.GT && v < 1 || o == Op.GE && v <= 1 || o == Op.NE && v == 0)
      return Function.EXISTS.get(input, ((FuncCall) expr[0]).expr);
    // EMPTY: c < (v<=1), c <= (v<1), c = (v=0)
    if(o == Op.LT && v <= 1 || o == Op.LE && v < 1 || o == Op.EQ && v == 0)
      return Function.EMPTY.get(input, ((FuncCall) expr[0]).expr);

    return this;
  }