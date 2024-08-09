  private static void sort(List<Var> vars) {
    Function<Var, Character> idFunction = v -> v.name;
    Function<Var, Set<Character>> predecessors =
        v -> v.dependencies.stream().filter(Character::isAlphabetic).collect(toSet());

    TopologicalSort<Var, Character> sort = new TopologicalSort<>(idFunction, predecessors);
    List<Var> newVars = sort.sort(vars);
    vars.clear();
    vars.addAll(newVars);
  }