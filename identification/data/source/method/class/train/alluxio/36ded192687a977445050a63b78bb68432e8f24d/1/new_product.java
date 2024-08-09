public static TransformDefinition parse(String definition) {
    // TODO(gpang): use real lexer/parser
    definition = definition.trim();

    if (definition.isEmpty()) {
      return new TransformDefinition(definition, Collections.emptyList());
    }

    // ';' separates actions
    String[] parts = definition.split(";");
    List<TransformAction> actions = new ArrayList<>(parts.length);
    for (String actionPart : parts) {
      actions.add(TransformAction.Parser.parse(actionPart));
    }

    return new TransformDefinition(definition, actions);
  }