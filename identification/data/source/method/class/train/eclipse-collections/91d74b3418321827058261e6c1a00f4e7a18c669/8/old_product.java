@Deprecated
    public static int count(String string, final org.eclipse.collections.impl.block.predicate.primitive.CharPredicate predicate)
    {
        return StringIterate.countChar(string, new CharPredicate()
        {
            public boolean accept(char value)
            {
                return predicate.accept(value);
            }
        });
    }