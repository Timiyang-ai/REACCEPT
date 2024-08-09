public static PsiMethod createOnEventMethod(
      PsiElement context, PsiClass eventClass, Collection<PsiParameter> additionalParameters) {

    final Project project = context.getProject();
    final PsiElementFactory factory = JavaPsiFacade.getElementFactory(project);

    final PsiType methodReturnType = PsiType.VOID;
    final String methodName = "on" + eventClass.getName();
    final PsiMethod method = factory.createMethod(methodName, methodReturnType, context);

    final PsiParameterList parameterList = method.getParameterList();
    final PsiParameter contextParameter =
        factory.createParameter(
            CONTEXT_PARAMETER_NAME,
            PsiType.getTypeByName(
                LithoClassNames.COMPONENT_CONTEXT_CLASS_NAME,
                project,
                GlobalSearchScope.allScope(project)),
            context);
    parameterList.add(contextParameter);

    final PsiField[] eventFields = eventClass.getFields();
    for (PsiField field : eventFields) {
      final String fieldName = field.getName();
      if (fieldName == null) {
        continue;
      }
      final PsiParameter parameter = factory.createParameter(fieldName, field.getType());
      final PsiModifierList parameterModifierList = parameter.getModifierList();
      if (parameterModifierList == null) {
        continue;
      }
      parameterModifierList.addAnnotation(LithoClassNames.FROM_EVENT_ANNOTATION_NAME);
      parameterList.add(parameter);
    }
    for (PsiParameter parameter : additionalParameters) {
      parameterList.add(parameter);
    }

    final PsiModifierList methodModifierList = method.getModifierList();
    methodModifierList.addAnnotation(
        LithoClassNames.ON_EVENT_ANNOTATION_NAME + "(" + eventClass.getQualifiedName() + ".class)");

    methodModifierList.setModifierProperty(PsiModifier.PACKAGE_LOCAL, true);
    methodModifierList.setModifierProperty(PsiModifier.STATIC, true);

    return method;
  }