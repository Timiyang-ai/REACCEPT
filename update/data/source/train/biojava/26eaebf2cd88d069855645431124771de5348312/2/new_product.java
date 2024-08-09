@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hit other = (Hit) obj;
        if (this.hitLen != other.hitLen) {
            return false;
        }
        if (this.hsps != other.hsps && (this.hsps == null || !this.hsps.equals(other.hsps))) {
            return false;
        }
        return true;
    }