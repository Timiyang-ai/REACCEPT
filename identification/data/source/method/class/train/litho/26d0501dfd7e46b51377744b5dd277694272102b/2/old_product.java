static ImmutableList<FieldModel> extractFields(PsiClass psiClass) {
    return Optional.of(psiClass)
        .map(PsiClass::getFields)
        .map(Arrays::stream)
        .map(
            fields ->
                fields
                    .map(
                        psiField -> {
                          Modifier[] empty = new Modifier[0];
                          PsiModifierList modifierList = psiField.getModifierList();
                          Modifier[] modifiers =
                              modifierList == null
                                  ? empty
                                  : PsiProcessingUtils.extractModifiers(modifierList)
                                      .toArray(empty);
                          return new FieldModel(
                              FieldSpec.builder(
                                      PsiTypeUtils.getTypeName(psiField.getType()),
                                      psiField.getName(),
                                      modifiers)
                                  .build(),
                              psiField);
                        })
                    .toArray(FieldModel[]::new))
        .map(ImmutableList::of)
        .orElse(ImmutableList.of());
  }