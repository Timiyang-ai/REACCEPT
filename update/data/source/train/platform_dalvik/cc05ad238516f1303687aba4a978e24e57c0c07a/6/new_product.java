public void add(Permission permission) {
        if (isReadOnly()) {
            throw new SecurityException(Messages.getString("security.15")); //$NON-NLS-1$
        }
        if (permission == null) {
            throw new IllegalArgumentException(Messages.getString("security.20")); //$NON-NLS-1$
        }

        Class<? extends Permission> inClass = permission.getClass();
        if (permClass != null) {
            if (permClass != inClass) {
                throw new IllegalArgumentException(Messages.getString("security.16", //$NON-NLS-1$
                    permission));
            }
        } else if( !(permission instanceof BasicPermission)) {
            throw new IllegalArgumentException(Messages.getString("security.16", //$NON-NLS-1$
                permission));
        } else { 
            // this is the first element provided that another thread did not add
            // BEGIN android-changed
            // copied from a newer version of harmony
            synchronized (this) {
                if (permClass != null && inClass != permClass) {
                    throw new IllegalArgumentException(Messages.getString("security.16", //$NON-NLS-1$
                        permission));
                }
                permClass = inClass;
            }
            // END android-changed
        }

        String name = permission.getName();
        items.put(name, permission);
        allEnabled = allEnabled || (name.length() == 1 && '*' == name.charAt(0));
    }