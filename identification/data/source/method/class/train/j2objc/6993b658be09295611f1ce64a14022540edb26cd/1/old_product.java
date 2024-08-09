protected Object clone() throws CloneNotSupportedException {
        AbstractMap<K,V> result = (AbstractMap<K,V>)super.clone();
        result.keySet = null;
        result.values = null;
        return result;
    }