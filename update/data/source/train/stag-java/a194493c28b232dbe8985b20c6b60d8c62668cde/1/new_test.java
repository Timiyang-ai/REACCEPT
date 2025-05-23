@Test
    public void getConcreteMembers_isCorrect() throws Exception {
        Element genericElement = Utils.getElementFromClass(DummyGenericClass.class);
        assertNotNull(genericElement);

        Map<FieldAccessor, TypeMirror> genericMembers = new HashMap<>();
        for (Element element : genericElement.getEnclosedElements()) {
            if (element instanceof VariableElement) {
                genericMembers.put(new DirectFieldAccessor((VariableElement) element), element.asType());
            }
        }

        TypeMirror concreteType =
                TypeUtils.getInheritedType(Utils.getElementFromClass(DummyInheritedClass.class));

        assertNotNull(concreteType);

        TypeMirror genericType = Utils.getGenericVersionOfClass(DummyGenericClass.class);

        assertNotNull(genericType);

        LinkedHashMap<FieldAccessor, TypeMirror> members =
                TypeUtils.getConcreteMembers(concreteType, (TypeElement) types.asElement(genericType), genericMembers);


        TypeMirror stringType = Utils.getTypeMirrorFromClass(String.class);
        assertNotNull(stringType);

        for (Entry<FieldAccessor, TypeMirror> entry : members.entrySet()) {
            if (entry.getKey().createGetterCode().contentEquals("testObject = ")) {

                assertTrue(entry.getValue().toString().equals(stringType.toString()));

            } else if (entry.getKey().createGetterCode().contentEquals("testList = ")) {

                assertTrue(entry.getValue()
                                   .toString()
                                   .equals(types.getDeclaredType(Utils.getElementFromClass(ArrayList.class),
                                           stringType).toString()));

            } else if (entry.getKey().createGetterCode().contentEquals("testMap = ")) {

                assertTrue(entry.getValue()
                                   .toString()
                                   .equals(types.getDeclaredType(Utils.getElementFromClass(HashMap.class), stringType,
                                                                 stringType).toString()));

            } else if (entry.getKey().createGetterCode().contentEquals("testSet = ")) {

                assertTrue(entry.getValue()
                                   .toString()
                                   .equals(types.getDeclaredType(Utils.getElementFromClass(HashSet.class), stringType)
                                                   .toString()));
            } else if (entry.getKey().createGetterCode().contentEquals("testArrayMap = ")) {
                TypeMirror listString = types.getDeclaredType(Utils.getElementFromClass(List.class), stringType);

                assertTrue(entry.getValue()
                        .toString()
                        .equals(types.getDeclaredType(Utils.getElementFromClass(HashMap.class), stringType, listString)
                                .toString()));
            } else if (entry.getKey().createGetterCode().contentEquals("testListMap = ")) {
                TypeMirror mapStringString = types.getDeclaredType(Utils.getElementFromClass(Map.class), stringType, stringType);
                assertTrue(entry.getValue()
                        .toString()
                        .equals(types.getDeclaredType(Utils.getElementFromClass(ArrayList.class), mapStringString)
                                .toString()));
            }
        }
    }