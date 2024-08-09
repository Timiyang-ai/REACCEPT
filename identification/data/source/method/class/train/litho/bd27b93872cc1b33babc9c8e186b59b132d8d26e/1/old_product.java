void addRemainingCompletions(Project project) {
    for (String qualifiedName : replacedQualifiedNames) {
      result.addElement(SpecLookupElement.create(qualifiedName, project));
    }
  }