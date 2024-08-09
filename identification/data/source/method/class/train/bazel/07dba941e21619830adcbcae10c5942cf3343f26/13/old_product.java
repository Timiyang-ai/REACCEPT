public String expand(String attrName, String attrValue) {
    int restart = 0;

    int attrLength = attrValue.length();
    StringBuilder result = new StringBuilder(attrValue.length());

    while (true) {
      // (1) find '$(location ' or '$(locations '
      String message = "$(location)";
      boolean multiple = false;
      int start = attrValue.indexOf(LOCATION, restart);
      int scannedLength = LOCATION.length();
      if (start == -1 || start + scannedLength == attrLength) {
        result.append(attrValue.substring(restart));
        break;
      }

      if (attrValue.charAt(start + scannedLength) == 's') {
        scannedLength++;
        if (start + scannedLength == attrLength) {
          result.append(attrValue.substring(restart));
          break;
        }
        message = "$(locations)";
        multiple = true;
      }

      if (attrValue.charAt(start + scannedLength) != ' ') {
        result.append(attrValue.substring(restart, start + scannedLength));
        restart = start + scannedLength;
        continue;
      }
      scannedLength++;

      int end = attrValue.indexOf(')', start + scannedLength);
      if (end == -1) {
        ruleContext.attributeError(attrName, "unterminated " + message + " expression");
        return attrValue;
      }

      // (2) parse label
      String labelText = attrValue.substring(start + scannedLength, end);
      Label label;
      try {
        label = ruleContext.getLabel().getRelative(labelText);
      } catch (Label.SyntaxException e) {
        ruleContext.attributeError(attrName,
                              "invalid label in " + message + " expression: " + e.getMessage());
        return attrValue;
      }

      // (3) replace with singleton artifact, iff unique.
      Collection<Artifact> artifacts = getLocationMap().get(label);
      if (artifacts == null) {
        ruleContext.attributeError(attrName,
                              "label '" + label + "' in " + message + " expression is not a "
                              + "declared prerequisite of this rule");
        return attrValue;
      }
      List<String> paths = getPaths(artifacts);
      if (paths.isEmpty()) {
        ruleContext.attributeError(attrName,
                              "label '" + label + "' in " + message + " expression expands to no "
                              + "files");
        return attrValue;
      }

      result.append(attrValue.substring(restart, start));
      if (multiple) {
        Collections.sort(paths);
        Joiner.on(' ').appendTo(result, paths);
      } else {
        if (paths.size() > 1) {
          ruleContext.attributeError(attrName,
              String.format(
                  "label '%s' in %s expression expands to more than one file, "
                      + "please use $(locations %s) instead.  Files (at most %d shown) are: %s",
                  label, message, label,
                  MAX_PATHS_SHOWN, Iterables.limit(paths, MAX_PATHS_SHOWN)));
          return attrValue;
        }
        result.append(Iterables.getOnlyElement(paths));
      }
      restart = end + 1;
    }
    return result.toString();
  }