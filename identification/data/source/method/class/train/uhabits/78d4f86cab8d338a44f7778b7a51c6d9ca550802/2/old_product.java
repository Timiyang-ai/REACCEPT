public boolean contains(long timestamp)
    {
        int count = select().where("timestamp = ?", timestamp).count();
        return (count > 0);
    }