@Override
    public void add(int location, E object) {
        checkValidObject(object);
        if (managedMode) {
            checkValidView();
            object = copyToRealmIfNeeded(object);
            view.insert(location, object.row.getIndex());
        } else {
            nonManagedList.add(location, object);
        }
    }