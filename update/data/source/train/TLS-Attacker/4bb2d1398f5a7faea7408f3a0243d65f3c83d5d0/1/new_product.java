public synchronized void release()
    {
        if (this.free == true)
        {
            throw new IllegalStateException("Trying to release an already released Server");
        }
        this.free = true;
    }