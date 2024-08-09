public long getLastAccessedTime() {
        if (lastAccessedTime == 0)
            return nativegetcreationtime();
        else
            return lastAccessedTime;
    }