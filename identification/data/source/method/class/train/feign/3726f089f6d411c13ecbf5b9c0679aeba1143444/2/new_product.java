public static String expand(String template, Map<String, ?> variables) {
    // skip expansion if there's no valid variables set. ex. {a} is the
    // first valid
    if (checkNotNull(template, "template").length() < 3)
      return template.toString();
    checkNotNull(variables, "variables for %s", template);

    boolean inVar = false;
    StringBuilder var = new StringBuilder();
    StringBuilder builder = new StringBuilder();
    for (char c : template.toCharArray()) {
      switch (c) {
        case '{':
          inVar = true;
          break;
        case '}':
          inVar = false;
          String key = var.toString();
          Object value = variables.get(var.toString());
          if (value != null)
            builder.append(value);
          else
            builder.append('{').append(key).append('}');
          var = new StringBuilder();
          break;
        default:
          if (inVar)
            var.append(c);
          else
            builder.append(c);
      }
    }
    return builder.toString();
  }