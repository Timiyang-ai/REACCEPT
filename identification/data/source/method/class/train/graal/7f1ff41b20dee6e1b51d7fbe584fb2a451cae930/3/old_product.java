@MethodSubstitution(isStatic = false)
    public static NodeClass getNodeClass(final Node thisObj) {
        Word klass = loadHub(thisObj);
        NodeClass nc = piCastExact(klass.readObject(Word.signed(klassNodeClassOffset()), KLASS_NODE_CLASS), NodeClass.class);
        if (nc != null) {
            return nc;
        }
        return getNodeClass(thisObj);
    }