    private void putRef(String name, ObjectId value, Map<String, Ref> holder) {
        refDb.putRef(name, value);
        holder.put(name, new Ref(name, value));
    }