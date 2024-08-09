public void changeField(PrefabValues prefabValues, TypeTag enclosingType) {
        modify(() -> {
            Object newValue = prefabValues.giveOther(TypeTag.of(field, enclosingType), field.get(object));
            field.set(object, newValue);
        }, false);
    }