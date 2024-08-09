@Deprecated
    public static void forEach(String string, final org.eclipse.collections.impl.block.procedure.primitive.CharProcedure procedure)
    {
        StringIterate.forEachChar(string, new CharProcedure()
        {
            public void value(char each)
            {
                procedure.value(each);
            }
        });
    }