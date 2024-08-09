@Deprecated
    public static void forEach(String string, org.eclipse.collections.impl.block.procedure.primitive.CharProcedure procedure)
    {
        StringIterate.forEachChar(string, procedure::value);
    }