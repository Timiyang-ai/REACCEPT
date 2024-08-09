public int size() {
        int count = 0;
        for (Node<E> p = first(); p != null; p = succ(p)) {
            if (p.getItem() != null) {
                // Collections.size() spec says to max out
                if (++count == Integer.MAX_VALUE)
                    break;
            }
        }
        return count;
    }