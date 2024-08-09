@Override
    public Object clone() {
        try {
            CopyOnWriteArrayList thisClone = (CopyOnWriteArrayList) super.clone();
            thisClone.setData(this.getData());
            return thisClone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("CloneNotSupportedException is not expected here");
        }
    }