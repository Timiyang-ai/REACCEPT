@NotNull
    public static LinkedHashMap<FieldAccessor, TypeMirror> getConcreteMembers(@NotNull TypeMirror concreteInherited,
                                                                              @NotNull TypeElement genericInherited,
                                                                              @NotNull Map<FieldAccessor, TypeMirror> members) {

        DebugLog.log(TAG, "Inherited concrete type: " + concreteInherited.toString());
        DebugLog.log(TAG, "Inherited generic type: " + genericInherited.asType().toString());
        List<? extends TypeMirror> concreteTypes = getParameterizedTypes(concreteInherited);
        List<? extends TypeMirror> inheritedTypes = getParameterizedTypes(genericInherited);

        LinkedHashMap<FieldAccessor, TypeMirror> map = new LinkedHashMap<>();

        for (Entry<FieldAccessor, TypeMirror> member : members.entrySet()) {

            DebugLog.log(TAG, "\t\tEvaluating member - " + member.getValue().toString());

            if (isConcreteType(member.getValue())) {

                DebugLog.log(TAG, "\t\t\tConcrete Type: " + member.getValue().toString());
                map.put(member.getKey(), member.getValue());

            } else {

                if (isParameterizedType(member.getValue())) {

                    // HashMap<String, T> ...
                    TypeMirror resolvedType = resolveTypeVars(member.getValue(), inheritedTypes, concreteTypes);
                    map.put(member.getKey(), resolvedType);

                    DebugLog.log(TAG, "\t\t\tGeneric Parameterized Type - " + member.getValue().toString() +
                                      " resolved to - " + resolvedType.toString());
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