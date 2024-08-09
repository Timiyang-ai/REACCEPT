  @Test
  public void getLocalAndInheritedMethods_Old() {
    Elements elements = compilation.getElements();
    Types types = compilation.getTypes();
    TypeMirror intMirror = types.getPrimitiveType(TypeKind.INT);
    TypeMirror longMirror = types.getPrimitiveType(TypeKind.LONG);
    TypeElement childType = elements.getTypeElement(Child.class.getCanonicalName());
    @SuppressWarnings("deprecation")
    Set<ExecutableElement> childTypeMethods =
        MoreElements.getLocalAndInheritedMethods(childType, elements);
    Set<ExecutableElement> objectMethods = visibleMethodsFromObject();
    assertThat(childTypeMethods).containsAtLeastElementsIn(objectMethods);
    Set<ExecutableElement> nonObjectMethods = Sets.difference(childTypeMethods, objectMethods);
    assertThat(nonObjectMethods).containsExactly(
            getMethod(ParentInterface.class, "bar", longMirror),
            getMethod(ParentClass.class, "foo"),
            getMethod(Child.class, "bar"),
            getMethod(Child.class, "baz"),
            getMethod(Child.class, "buh", intMirror),
            getMethod(Child.class, "buh", intMirror, intMirror))
        .inOrder();;
  }