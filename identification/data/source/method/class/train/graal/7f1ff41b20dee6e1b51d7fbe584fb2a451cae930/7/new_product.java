@MethodSubstitution(isStatic = false)
    public static NodeClass getNodeClass(final Node node) {
        // HotSpot creates the NodeClass for each Node subclass while initializing it
        // so we are guaranteed to read a non-null value here. As long as NodeClass
        // is final, the stamp of the PiNode below will automatically be exact.
        Pointer klass = loadHub(node);
        return piCastNonNull(klass.readObject(Word.signed(instanceKlassNodeClassOffset()), KLASS_NODE_CLASS), NodeClass.class);
    }