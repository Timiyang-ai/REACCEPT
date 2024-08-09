public void move(int oldPos, int newPos) {
        if (managedMode) {
            checkValidView();
            view.move(oldPos, newPos);
        } else {
            checkIndex(oldPos);
            checkIndex(newPos);
            E object = nonManagedList.remove(oldPos);
            if (newPos > oldPos) {
                nonManagedList.add(newPos - 1, object);
            } else {
                nonManagedList.add(newPos, object);
            }
        }
    }