public TypeSpec generateAdapterForCustomType(Type type) {
    NameAllocator nameAllocator = nameAllocators.getUnchecked(type);
    ClassName adapterTypeName = abstractAdapterName(type.type());
    ClassName typeName = (ClassName) typeName(type.type());

    TypeSpec.Builder adapter;
    if (type instanceof MessageType) {
      adapter = messageAdapter(nameAllocator, (MessageType) type, typeName, adapterTypeName, null)
          .toBuilder();
    } else {
      adapter = enumAdapter(nameAllocator, (EnumType) type, typeName, adapterTypeName).toBuilder();
    }

    if (adapterTypeName.enclosingClassName() != null) adapter.addModifiers(STATIC);

    for (Type nestedType : type.nestedTypes()) {
      if (profile.getAdapter(nestedType.type()) == null) {
        throw new IllegalArgumentException("Missing custom proto adapter for "
            + nestedType.type().enclosingTypeOrPackage() + "." + nestedType.type().simpleName()
            + " when enclosing proto has custom proto adapter.");
      }
      adapter.addType(generateAdapterForCustomType(nestedType));
    }

    return adapter.build();
  }