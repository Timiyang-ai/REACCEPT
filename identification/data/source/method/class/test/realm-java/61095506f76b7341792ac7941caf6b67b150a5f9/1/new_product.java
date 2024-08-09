@Override
    public void add(int location, E object) {
        checkValidObject(object);
        if (managedMode) {
            checkValidView();
            if (location < 0 || location > size()) {
                throw new IndexOutOfBoundsException("Invalid index " + location + ", size is " + size());
            }
            object = copyToRealmIfNeeded(object);
            view.insert(location, object.row.getIndex());
        } else {
            nonManagedList.add(location, object);
        }
    }