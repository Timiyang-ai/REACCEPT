@Test
    public void getConcreteMembers_isCorrect() throws Exception {
        Element genericElement = Utils.getElementFromClass(DummyGenericClass.class);
        assertNotNull(genericElement);
        Map<Element, TypeMirror> genericMembers = new HashMap<>();
        for (Element element : genericElement.getEnclosedElements()) {
            if (element instanceof VariableElement) {
                genericMembers.put(element, element.asType());
            }
        }

        TypeMirror concreteType =
                TypeUtils.getInheritedType(Utils.getElementFromClass(DummyInheritedClass.class));

        assertNotNull(concreteType);

        TypeMirror genericType = Utils.getGenericVersionOfClass(DummyGenericClass.class);

        assertNotNull(genericType);

        LinkedHashMap<Element, TypeMirror> members =
                TypeUtils.getConcreteMembers(concreteType, types.asElement(genericType), genericMembers);


        TypeMirror stringType = Utils.getTypeMirrorFromClass(String.class);
        assertNotNull(stringType);

        for (Entry<Element, TypeMirror> entry : members.entrySet()) {
            if (entry.getKey().getSimpleName().contentEquals("testObject")) {

                assertTrue(entry.getValue().toString().equals(stringType.toString()));

            } else if (entry.getKey().getSimpleName().contentEquals("testList")) {

                assertTrue(entry.getValue()
                        .toString()
                        .equals(types.getDeclaredType(
                                (TypeElement) Utils.getElementFromClass(ArrayList.class),
                                stringType).toString()));

            } else if (entry.getKey().getSimpleName().contentEquals("testMap")) {

                assertTrue(entry.getValue()
                        .toString()
                        .equals(types.getDeclaredType(
                                (TypeElement) Utils.getElementFromClass(HashMap.class), stringType,
                                stringType).toString()));

            } else if (entry.getKey().getSimpleName().contentEquals("testSet")) {

                assertTrue(entry.getValue()
                        .toString()
                        .equals(types.getDeclaredType(
                                (TypeElement) Utils.getElementFromClass(HashSet.class), stringType)
                                .toString()));
            }
        }
    }