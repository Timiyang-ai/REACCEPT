public void remove(int value) {
        int index = ints.indexOf(value);

        if (index >= 0) {
            ints.removeIndex(index);
        }
    }