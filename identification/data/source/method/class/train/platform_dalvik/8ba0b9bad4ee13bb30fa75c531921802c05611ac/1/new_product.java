public Object clone() {
        try {
            CopyOnWriteArrayList c = (CopyOnWriteArrayList)(super.clone());
            c.resetLock();
            return c;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }