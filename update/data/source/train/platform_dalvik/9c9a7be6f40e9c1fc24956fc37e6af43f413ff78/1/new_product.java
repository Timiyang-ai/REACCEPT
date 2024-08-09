public long getLastAccessedTime() {
        return (lastAccessedTime == 0) ? getCreationTime() : lastAccessedTime;
    }