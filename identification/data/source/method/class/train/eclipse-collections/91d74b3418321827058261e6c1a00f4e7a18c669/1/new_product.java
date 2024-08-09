@Deprecated
    public static int count(String string, org.eclipse.collections.impl.block.predicate.primitive.CharPredicate predicate)
    {
        return StringIterate.countChar(string, predicate::accept);
    }