public void changeField(PrefabValues prefabValues, TypeTag enclosingType) {
        modify(new FieldChanger(prefabValues, enclosingType));
    }