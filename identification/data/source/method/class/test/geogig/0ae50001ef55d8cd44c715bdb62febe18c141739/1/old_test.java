    private void putSymRef(String name, String value, Map<String, Ref> holder) {
        refDb.putSymRef(name, value);
        holder.put(name, refDb.get(name).get());
    }