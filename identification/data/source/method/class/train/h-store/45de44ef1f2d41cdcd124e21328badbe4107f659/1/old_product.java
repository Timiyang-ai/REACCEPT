public Collection<ProfileMeasurement> getProfileMeasurements() {
        List<ProfileMeasurement> ret = new ArrayList<ProfileMeasurement>();
        for (Field f : this.getClass().getDeclaredFields()) {
            Object obj = null;
            try {
                obj = f.get(this);
            } catch (Exception ex) {
                throw new RuntimeException("Failed to get value for field '" + f.getName() + "'", ex);
            }
            if (obj instanceof ProfileMeasurement) {
                ret.add((ProfileMeasurement)obj);
            }
        } // FOR
        return (ret);
    }