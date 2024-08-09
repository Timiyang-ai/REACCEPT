public static <KType> KTypeOpenHashSet<KType> from(KType... elements)
    {
        final KTypeOpenHashSet<KType> set = new KTypeOpenHashSet<KType>(
            // NOCOMMIT: LEFTOVER!
            (int) (elements.length * (1 + DEFAULT_LOAD_FACTOR)));
        set.add(elements);
        return set;
    }