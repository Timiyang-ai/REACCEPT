static ImmutableList<FieldModel> extractFields(PsiClass psiClass) {
    return Optional.of(psiClass)
        .map(PsiClass::getFields)
        .map(Arrays::stream)
        .map(
            fields ->
                fields
                    .filter(Objects::nonNull)
                    .map(PsiFieldsExtractor::createFieldModel)
                    .collect(Collectors.toCollection(ImmutableList::of)))
        .orElse(ImmutableList.of());
  }