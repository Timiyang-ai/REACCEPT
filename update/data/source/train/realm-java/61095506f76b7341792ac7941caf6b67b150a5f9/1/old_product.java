public void removeFromRealm() {
        if (row == null) {
            throw new IllegalStateException("Object malformed: missing object in Realm. Make sure to instantiate RealmObjects with Realm.createObject()");
        }
        if (realm == null) {
            throw new IllegalStateException("Object malformed: missing Realm. Make sure to instantiate RealmObjects with Realm.createObject()");
        }
        realm.checkIfValid();

        row.getTable().moveLastOver(row.getIndex());
        row = InvalidRow.INSTANCE;
    }