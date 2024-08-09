static Vector<Long> rangeClosedBy(long from, long toInclusive, long step) {
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0");
        } else if (from == toInclusive) {
            return Vector.of(from);
        } else if (step * (from - toInclusive) > 0) {
            return Vector.empty();
        } else {
            HashArrayMappedTrie<Integer, Long> trie = HashArrayMappedTrie.empty();
            if (step > 0) {
                long i = from;
                while (i <= toInclusive) {
                    trie = trie.put(trie.size(), i);
                    if(Long.MAX_VALUE - step < i) {
                        break;
                    }
                    i += step;
                }
            } else {
                long i = from;
                while (i >= toInclusive) {
                    trie = trie.put(trie.size(), i);
                    if(Long.MIN_VALUE - step > i) {
                        break;
                    }
                    i += step;
                }
            }
            return ofTrie(trie);
        }
    }