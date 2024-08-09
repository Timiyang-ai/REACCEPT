public static Map<JsName, JsLiteral> exec(JProgram jprogram, JsProgram program,
      byte whatToIntern) {
    LiteralInterningVisitor v = new LiteralInterningVisitor(jprogram, program.getScope(), false,
        computeOccurrenceCounts(program), whatToIntern);
    v.accept(program);

    Map<Integer, Set<JsLiteral>> bins = Maps.newHashMap();
    for (int i = 0, j = program.getFragmentCount(); i < j; i++) {
      bins.put(i, Sets.<JsLiteral>newLinkedHashSet());
    }
    for (Map.Entry<JsLiteral, Integer> entry : v.fragmentAssignment.entrySet()) {
      Set<JsLiteral> set = bins.get(entry.getValue());
      assert set != null;
      set.add(entry.getKey());
    }

    for (Map.Entry<Integer, Set<JsLiteral>> entry : bins.entrySet()) {
      createVars(program, program.getFragment(entry.getKey()).getGlobalBlock(),
          entry.getValue(), v.variableNameForInternedLiteral);
    }

    return reverse(v.variableNameForInternedLiteral);
  }