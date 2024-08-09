public void move(int oldPos, int newPos) {
        if (managedMode) {
            checkValidView();
            view.move(oldPos, newPos);
        } else {
            checkIndex(oldPos);
            checkIndex(newPos);
            E object = unmanagedList.remove(oldPos);
            if (newPos > oldPos) {
                unmanagedList.add(newPos - 1, object);
            } else {
                unmanagedList.add(newPos, object);
            }
        }
    }