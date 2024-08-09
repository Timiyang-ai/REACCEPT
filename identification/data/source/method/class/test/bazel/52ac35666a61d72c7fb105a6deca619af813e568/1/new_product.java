public TargetPattern parse(String pattern) throws TargetParsingException {
      // The structure of this method is by cases, according to the usage string
      // constant (see lib/blaze/commands/target-syntax.txt).

      String originalPattern = pattern;
      final boolean includesRepo = pattern.startsWith("@");
      RepositoryName repository = PackageIdentifier.DEFAULT_REPOSITORY_NAME;
      if (includesRepo) {
        int pkgStart = pattern.indexOf("//");
        if (pkgStart < 0) {
          throw new TargetParsingException("Couldn't find package in target " + pattern);
        }
        try {
          repository = RepositoryName.create(pattern.substring(0, pkgStart));
        } catch (LabelSyntaxException e) {
          throw new TargetParsingException(e.getMessage());
        }

        pattern = pattern.substring(pkgStart);
      }
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
        PackageIdentifier packageIdentifier;
        try {
          packageIdentifier = PackageIdentifier.parse(
              repository.getName() + "//" + realPackagePart);
        } catch (LabelSyntaxException e) {
          throw new TargetParsingException(
              "Invalid package name '" + realPackagePart + "': " + e.getMessage());
        }
        if (targetPart.isEmpty() || ALL_RULES_IN_SUFFIXES.contains(targetPart)) {
          return new TargetsBelowDirectory(
              originalPattern, relativeDirectory, packageIdentifier, true);
        } else if (ALL_TARGETS_IN_SUFFIXES.contains(targetPart)) {
          return new TargetsBelowDirectory(
              originalPattern, relativeDirectory, packageIdentifier, false);
        }
      }

      if (ALL_RULES_IN_SUFFIXES.contains(targetPart)) {
        PackageIdentifier packageIdentifier;
        try {
          packageIdentifier = PackageIdentifier.parse(repository.getName() + "//" + packagePart);
        } catch (LabelSyntaxException e) {
          throw new TargetParsingException(
              "Invalid package name '" + packagePart + "': " + e.getMessage());
        }
        return new TargetsInPackage(originalPattern, relativeDirectory, packageIdentifier,
            targetPart, isAbsolute, true, true);
      }

      if (ALL_TARGETS_IN_SUFFIXES.contains(targetPart)) {
        PackageIdentifier packageIdentifier;
        try {
          packageIdentifier = PackageIdentifier.parse(repository.getName() + "//" + packagePart);
        } catch (LabelSyntaxException e) {
          throw new TargetParsingException(
              "Invalid package name '" + packagePart + "': " + e.getMessage());
        }
        return new TargetsInPackage(originalPattern, relativeDirectory, packageIdentifier,
            targetPart, isAbsolute, false, true);
      }

      if (includesRepo || isAbsolute || pattern.contains(":")) {
        PackageIdentifier packageIdentifier;
        String fullLabel = repository.getName() + "//" + pattern;
        try {
          PackageAndTarget packageAndTarget = LabelValidator.validateAbsoluteLabel(fullLabel);
          packageIdentifier = PackageIdentifier.create(repository,
              new PathFragment(packageAndTarget.getPackageName()));
        } catch (BadLabelException e) {
          String error = "invalid target format '" + originalPattern + "': " + e.getMessage();
          throw new TargetParsingException(error);
        }
        return new SingleTarget(
            fullLabel, packageIdentifier, originalPattern, relativeDirectory);
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
      try {
        PackageIdentifier.parse("//" + packageName);
      } catch (LabelSyntaxException e) {
        throw new TargetParsingException(
            "Bad target pattern '" + originalPattern + "': " + e.getMessage());
      }
      return new InterpretPathAsTarget(pattern, originalPattern, relativeDirectory);
    }