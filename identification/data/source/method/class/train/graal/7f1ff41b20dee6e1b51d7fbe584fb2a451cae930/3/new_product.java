@MethodSubstitution(isStatic = false)
    public static NodeClass getNodeClass(final Node node) {
        // HotSpot creates the NodeClass for each Node subclass while initializing it
        // so we are guaranteed to read a non-null value here. The fact that NodeClass
        // is final will automatically make the stamp of the PiNode exact.
        Word klass = loadHub(node);
        return piCastNonNull(klass.readObject(Word.signed(klassNodeClassOffset()), KLASS_NODE_CLASS), NodeClass.class);
    }