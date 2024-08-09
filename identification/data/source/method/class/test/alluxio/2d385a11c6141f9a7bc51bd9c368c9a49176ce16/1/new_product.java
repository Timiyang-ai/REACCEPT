public static TransformAction parse(String definition) {
      definition = definition.trim();

      String actionName = null;
      List<String> argList = Collections.emptyList();
      Map<String, String> options = new HashMap<>();
      while (!definition.isEmpty()) {
        Matcher matcher = COMPONENT_REGEX.matcher(definition);
        if (!matcher.find()) {
          throw new IllegalArgumentException(
              ExceptionMessage.TRANSFORM_ACTION_PARSE_FAILED.getMessage(definition));
        }
        String name = matcher.group("name");
        String args = matcher.group("args");
        definition = definition.substring(matcher.end()).trim();
        if (!definition.isEmpty()) {
          if (!definition.startsWith(".")) {
            throw new IllegalArgumentException("Missing '.' at: " + definition);
          }
          definition = definition.substring(1);
        }
        if (actionName == null) {
          actionName = name;
          argList = parseArgList(args);
          // continue to parse the next component.
          continue;
        }

        // any additional components must be the 'option' component.
        if (!name.equals("option")) {
          throw new IllegalArgumentException("Expected an 'option()' component at: " + definition);
        }

        // This is an option() component.
        List<String> optionArgs = parseArgList(args);
        if (optionArgs.size() != 2) {
          throw new IllegalArgumentException(
              String.format("Incorrect # args for option() component. args: %s", args));
        }
        options.put(optionArgs.get(0), optionArgs.get(1));
      }

      return TransformActionRegistry.create(definition, actionName, argList, options);
    }