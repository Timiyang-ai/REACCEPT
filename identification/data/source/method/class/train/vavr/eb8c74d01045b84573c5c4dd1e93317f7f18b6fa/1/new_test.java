    @Override
    protected <T1 extends Comparable<T1>, T2> HashMultimap<T1, T2> emptyMap(Comparator<? super T2> comparator) {
        switch (containerType) {
            case SEQ:
                return HashMultimap.withSeq().empty();
            case SET:
                return HashMultimap.withSet().empty();
            case SORTED_SET:
                return HashMultimap.withSortedSet(comparator).empty();
            default:
                throw new RuntimeException();
        }
    }