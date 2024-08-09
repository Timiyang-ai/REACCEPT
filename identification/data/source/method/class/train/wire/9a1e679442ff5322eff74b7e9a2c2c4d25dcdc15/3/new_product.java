public TypeSpec generateAbstractAdapter(MessageType type) {
    NameAllocator nameAllocator = nameAllocators.getUnchecked(type);
    ClassName adapterTypeName = abstractAdapterName(type.type());
    ClassName typeName = (ClassName) typeName(type.type());
    TypeSpec.Builder adapter = messageAdapter(
        nameAllocator, type, typeName, adapterTypeName, null).toBuilder();

    for (Type nestedType : type.nestedTypes()) {
      if (profile.getAdapter(nestedType.type()) == null) {
        throw new IllegalArgumentException("Missing custom proto adapter for "
            + nestedType.type().enclosingTypeOrPackage() + "." + nestedType.type().simpleName()
            + " when enclosing proto has custom proto adapter.");
      }
      if (nestedType instanceof MessageType) {
        adapter.addType(generateAbstractAdapter((MessageType) nestedType));
      }
    }

    return adapter.build();
  }