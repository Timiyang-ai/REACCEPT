public final ProfileMeasurement[] getProfileMeasurements() {
        if (pm_cache == null) {
            synchronized (this) {
                if (pm_cache == null) {
                    final List<ProfileMeasurement> temp = new ArrayList<ProfileMeasurement>();
                    for (Field f : this.getClass().getDeclaredFields()) {
                        int modifiers = f.getModifiers();
                        if (Modifier.isTransient(modifiers) == false &&
                            Modifier.isPrivate(modifiers) == false &&
                            Modifier.isStatic(modifiers) == false) {
                            
                            Object obj = null;
                            try {
                                obj = f.get(this);
                            } catch (Exception ex) {
                                throw new RuntimeException("Failed to get value for field '" + f.getName() + "'", ex);
                            }
                            if (obj instanceof ProfileMeasurement) temp.add((ProfileMeasurement)obj);
                        }
                    } // FOR
                    pm_cache = temp.toArray(new ProfileMeasurement[temp.size()]);
                }
            } // SYNCH
        }
        return (pm_cache);
    }