static Vector<Integer> rangeClosedBy(int from, int toInclusive, int step) {
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0");
        } else if (from == toInclusive) {
            return Vector.of(from);
        } else if (step * (from - toInclusive) > 0) {
            return Vector.empty();
        } else {
            HashArrayMappedTrie<Integer, Integer> trie = HashArrayMappedTrie.empty();
            if (step > 0) {
                int i = from;
                while (i <= toInclusive) {
                    trie = trie.put(trie.size(), i);
                    if(Integer.MAX_VALUE - step < i) {
                        break;
                    }
                    i += step;
                }
            } else {
                int i = from;
                while (i >= toInclusive) {
                    trie = trie.put(trie.size(), i);
                    if(Integer.MIN_VALUE - step > i) {
                        break;
                    }
                    i += step;
                }
            }
            return trie.size() == 0 ? empty() : new Impl<>(trie);
        }
    }