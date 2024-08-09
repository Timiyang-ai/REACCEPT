private void readObject(java.io.ObjectInputStream in) throws IOException,
        ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();

        items = new HashMap<String, Permission>();
        synchronized (items) {
            permClass = (Class<? extends Permission>)fields.get("permClass", null); //$NON-NLS-1$
            items.putAll((Hashtable<String, Permission>) fields.get(
                    "permissions", new Hashtable<String, Permission>())); //$NON-NLS-1$
            for (Iterator<Permission> iter = items.values().iterator(); iter.hasNext();) {
                if (iter.next().getClass() != permClass) {
                    throw new InvalidObjectException(Messages.getString("security.24")); //$NON-NLS-1$
                }
            }
            allEnabled = fields.get("all_allowed", false); //$NON-NLS-1$
            if (allEnabled && !items.containsKey("*")) { //$NON-NLS-1$
                throw new InvalidObjectException(Messages.getString("security.25")); //$NON-NLS-1$
            }
        }
    }