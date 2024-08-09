public void move(int oldPos, int newPos) {
        if (isManaged()) {
            checkValidRealm();
            osListOperator.move(oldPos, newPos);
        } else {
            final int listSize = unmanagedList.size();
            if (oldPos < 0 || listSize <= oldPos) {
                throw new IndexOutOfBoundsException("Invalid index " + oldPos + ", size is " + listSize);
            }
            if (newPos < 0 || listSize <= newPos) {
                throw new IndexOutOfBoundsException("Invalid index " + newPos + ", size is " + listSize);
            }
            E object = unmanagedList.remove(oldPos);
            if (newPos > oldPos) {
                unmanagedList.add(newPos - 1, object);
            } else {
                unmanagedList.add(newPos, object);
            }
        }
    }