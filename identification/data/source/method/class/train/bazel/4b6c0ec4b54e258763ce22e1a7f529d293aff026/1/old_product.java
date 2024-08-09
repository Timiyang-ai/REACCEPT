@Nullable
  public Class<?> getCoreInterfaceRewritingTarget(
      int opcode, String owner, String name, String desc, boolean itf) {
    if (owner.contains("$$Lambda$") || owner.endsWith("$$CC")) {
      // Regular desugaring handles generated classes, no emulation is needed
      return null;
    }
    if (!itf && (opcode == Opcodes.INVOKESTATIC || opcode == Opcodes.INVOKESPECIAL)) {
      // Ignore staticly dispatched invocations on classes--they never need rewriting
      return null;
    }
    Class<?> clazz;
    if (isRenamedCoreLibrary(owner)) {
      // For renamed invocation targets we just need to do what InterfaceDesugaring does, that is,
      // only worry about invokestatic and invokespecial interface invocations; nothing to do for
      // invokevirtual and invokeinterface.  InterfaceDesugaring ignores bootclasspath interfaces,
      // so we have to do its work here for renamed interfaces.
      if (itf
          && (opcode == Opcodes.INVOKESTATIC || opcode == Opcodes.INVOKESPECIAL)) {
        clazz = loadFromInternal(owner);
      } else {
        return null;
      }
    } else {
      // If not renamed, see if the owner needs emulation.
      clazz = getEmulatedCoreClassOrInterface(owner);
      if (clazz == null) {
        return null;
      }
    }
    checkArgument(itf == clazz.isInterface(), "%s expected to be interface: %s", owner, itf);

    if (opcode == Opcodes.INVOKESTATIC) {
      // Static interface invocation always goes to the given owner
      checkState(itf); // we should've bailed out above.
      return clazz;
    }

    // See if the invoked method is a default method, which will need rewriting.  For invokespecial
    // we can only get here if its a default method, and invokestatic we handled above.
    Method callee = findInterfaceMethod(clazz, name, desc);
    if (callee != null && callee.isDefault()) {
      Class<?> result = callee.getDeclaringClass();
      if (isRenamedCoreLibrary(result.getName().replace('.', '/'))
          || emulatedInterfaces.stream().anyMatch(emulated -> emulated.isAssignableFrom(result))) {
        return result;
      }
      // We get here if the declaring class is a supertype of an emulated interface.  In that case
      // use the emulated interface instead (since we don't desugar the supertype).  Fail in case
      // there are multiple possibilities.
      Iterator<Class<?>> roots =
          emulatedInterfaces
              .stream()
              .filter(
                  emulated -> emulated.isAssignableFrom(clazz) && result.isAssignableFrom(emulated))
              .iterator();
      checkState(roots.hasNext()); // must exist
      Class<?> substitute = roots.next();
      checkState(!roots.hasNext(), "Ambiguous emulation substitute: %s", callee);
      return substitute;
    } else {
      checkArgument(opcode != Opcodes.INVOKESPECIAL,
          "Couldn't resolve interface super call %s.super.%s : %s", owner, name, desc);
    }
    return null;
  }