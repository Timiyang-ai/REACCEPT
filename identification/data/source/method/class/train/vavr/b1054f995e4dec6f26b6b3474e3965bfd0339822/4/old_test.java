    @Override
    protected <T1 extends Comparable<T1>, T2> TreeMultimap<T1, T2> emptyMap(Comparator<? super T2> comparator) {
        switch (containerType) {
            case SEQ:
                return TreeMultimap.withSeq().empty();
            case SET:
                return TreeMultimap.withSet().empty();
            case SORTED_SET:
                return TreeMultimap.withSortedSet(comparator).empty();
            default:
                throw new RuntimeException();
        }
    }