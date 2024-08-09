public TargetPattern parse(String pattern) throws TargetParsingException {
      // The structure of this method is by cases, according to the usage string
      // constant (see lib/blaze/commands/target-syntax.txt).

      String originalPattern = pattern;
      final boolean isAbsolute = pattern.startsWith("//");

      // We now absolutize non-absolute target patterns.
      pattern = isAbsolute ? pattern.substring(2) : absolutize(pattern);
      // Check for common errors.
      if (pattern.startsWith("/")) {
        throw new TargetParsingException("not a relative path or label: '" + pattern + "'");
      }
      if (pattern.isEmpty()) {
        throw new TargetParsingException("the empty string is not a valid target");
      }

      // Transform "/BUILD" suffix into ":BUILD" to accept //foo/bar/BUILD
      // syntax as a synonym to //foo/bar:BUILD.
      if (pattern.endsWith("/BUILD")) {
        pattern = pattern.substring(0, pattern.length() - 6) + ":BUILD";
      }

      int colonIndex = pattern.lastIndexOf(':');
      String packagePart = colonIndex < 0 ? pattern : pattern.substring(0, colonIndex);
      String targetPart = colonIndex < 0 ? "" : pattern.substring(colonIndex + 1);

      if (packagePart.equals("...")) {
        packagePart = "/...";  // special case this for easier parsing
      }

      if (packagePart.endsWith("/")) {
        throw new TargetParsingException("The package part of '" + originalPattern
            + "' should not end in a slash");
      }

      if (packagePart.endsWith("/...")) {
        String realPackagePart = removeSuffix(packagePart, "/...");
        if (targetPart.isEmpty() || ALL_RULES_IN_SUFFIXES.contains(targetPart)) {
          return new TargetsBelowPackage(originalPattern, realPackagePart, true);
        } else if (ALL_TARGETS_IN_SUFFIXES.contains(targetPart)) {
          return new TargetsBelowPackage(originalPattern, realPackagePart, false);
        }
      }

      if (ALL_RULES_IN_SUFFIXES.contains(targetPart)) {
        return new TargetsInPackage(
            originalPattern, pattern, ":" + targetPart, isAbsolute, true, true);
      }

      if (ALL_TARGETS_IN_SUFFIXES.contains(targetPart)) {
        return new TargetsInPackage(
            originalPattern, pattern, ":" + targetPart, isAbsolute, false, true);
      }


      if (isAbsolute || pattern.contains(":")) {
        String fullLabel = "//" + pattern;
        try {
          LabelValidator.validateAbsoluteLabel(fullLabel);
        } catch (BadLabelException e) {
          String error = "invalid target format '" + originalPattern + "': " + e.getMessage();
          throw new TargetParsingException(error);
        }
        return new SingleTarget(fullLabel);
      }

      // This is a stripped-down version of interpretPathAsTarget that does no I/O.  We have a basic
      // relative path. e.g. "foo/bar/Wiz.java". The strictest correct check we can do here (without
      // I/O) is just to ensure that there is *some* prefix that is a valid package-name. It's
      // sufficient to test the first segment. This is really a rather weak check; perhaps we should
      // just eliminate it.
      int slashIndex = pattern.indexOf('/');
      String packageName = pattern;
      if (slashIndex > 0) {
        packageName = pattern.substring(0, slashIndex);
      }
      String errorMessage = LabelValidator.validatePackageName(packageName);
      if (errorMessage != null) {
        throw new TargetParsingException("Bad target pattern '" + originalPattern + "': " +
            errorMessage);
      }
      return new InterpretPathAsTarget(pattern);
    }