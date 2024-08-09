protected static ITestNGMethod[] findDependedUponMethods(ITestNGMethod m, ITestNGMethod[] methods) {

    String canonicalMethodName = calculateMethodCanonicalName(m);

    List<ITestNGMethod> vResult = Lists.newArrayList();
    String regexp = null;
    for (String fullyQualifiedRegexp : m.getMethodsDependedUpon()) {
      boolean foundAtLeastAMethod = false;

      if (null != fullyQualifiedRegexp) {
        // Escapes $ in regexps as it is not meant for end - line matching, but inner class matches.
        regexp = fullyQualifiedRegexp.replace("$", "\\$");
        MatchResults results = matchMethod(methods, regexp);
        foundAtLeastAMethod = results.foundAtLeastAMethod;
        vResult.addAll(results.matchedMethods);
        if (!foundAtLeastAMethod) {
          //Replace the declaring class name in the dependsOnMethods value with
          //the fully qualified test class name and retry the method matching.
          int lastIndex = regexp.lastIndexOf('.');
          String newMethodName;
          if (lastIndex != -1) {
            newMethodName = m.getTestClass().getRealClass().getName()  + regexp.substring(lastIndex);
            results =  matchMethod(methods, newMethodName);
            foundAtLeastAMethod = results.foundAtLeastAMethod;
            vResult.addAll(results.matchedMethods);
          }
        }
      }

      if (!foundAtLeastAMethod) {
        if (m.ignoreMissingDependencies()) {
          continue;
        }
        if (m.isAlwaysRun()) {
          continue;
        }
        Method maybeReferringTo = findMethodByName(m, regexp);
        if (maybeReferringTo != null) {
          throw new TestNGException(canonicalMethodName + "() is depending on method "
              + maybeReferringTo + ", which is not annotated with @Test or not included.");
        }
        throw new TestNGException(canonicalMethodName
            + "() depends on nonexistent method " + regexp);
      }
    }//end for

    return vResult.toArray(new ITestNGMethod[vResult.size()]);
  }