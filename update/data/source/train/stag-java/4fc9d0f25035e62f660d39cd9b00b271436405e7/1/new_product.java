@NotNull
    public static LinkedHashMap<Element, TypeMirror> getConcreteMembers(@NotNull TypeMirror concreteInherited,
                                                              @NotNull Element genericInherited,
                                                              @NotNull Map<Element, TypeMirror> members) {

        DebugLog.log(TAG, "Inherited concrete type: " + concreteInherited.toString());
        DebugLog.log(TAG, "Inherited generic type: " + genericInherited.asType().toString());
        List<? extends TypeMirror> concreteTypes = getParameterizedTypes(concreteInherited);
        List<? extends TypeMirror> inheritedTypes = getParameterizedTypes(genericInherited);

        LinkedHashMap<Element, TypeMirror> map = new LinkedHashMap<>();

        for (Entry<Element, TypeMirror> member : members.entrySet()) {

            DebugLog.log(TAG, "\t\tEvaluating member - " + member.getValue().toString());

            if (isConcreteType(member.getValue())) {

                DebugLog.log(TAG, "\t\t\tConcrete Type: " + member.getValue().toString());
                map.put(member.getKey(), member.getValue());

            } else {

                if (isParameterizedType(member.getValue())) {

                    // HashMap<String, T> ...

                    List<TypeMirror> genericTypes = getMemberTypes(member.getValue());


                    List<TypeMirror> concreteGenericTypes = new ArrayList<>();

                    for (TypeMirror genericType : genericTypes) {
                        if (isConcreteType(genericType)) {
                            concreteGenericTypes.add(genericType);
                        } else {
                            int index = inheritedTypes.indexOf(genericType);
                            concreteGenericTypes.add(concreteTypes.get(index));
                        }
                    }

                    TypeElement typeElement = (TypeElement) sTypeUtils.asElement(member.getValue());
                    TypeMirror[] concreteTypeArray =
                            concreteGenericTypes.toArray(new TypeMirror[concreteGenericTypes.size()]);

                    DeclaredType declaredType = sTypeUtils.getDeclaredType(typeElement, concreteTypeArray);

                    map.put(member.getKey(), declaredType);

                    DebugLog.log(TAG, "\t\t\tGeneric Parameterized Type - " + member.getValue().toString() +
                            " resolved to - " + declaredType.toString());
                } else {

                    int index = inheritedTypes.indexOf(member.getKey().asType());
                    TypeMirror concreteType = concreteTypes.get(index);
                    map.put(member.getKey(), concreteType);

                    DebugLog.log(TAG, "\t\t\tGeneric Type - " + member.getValue().toString() +
                            " resolved to - " + concreteType.toString());
                }
            }
        }
        return map;
    }