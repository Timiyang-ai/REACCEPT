@Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.hitLen;
        hash = 89 * hash + (this.hsps != null ? this.hsps.hashCode() : 0);
        return hash;
    }