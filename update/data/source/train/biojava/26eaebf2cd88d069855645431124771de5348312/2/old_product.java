@Override
    public boolean equals(Object o){
        if (!(o instanceof Hit)) return false;
        
        return o.hashCode() == this.hashCode();
    }