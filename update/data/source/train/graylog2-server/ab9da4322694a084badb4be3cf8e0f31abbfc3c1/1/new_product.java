public void insertGelfMessage(GELFMessage message) throws Exception {
        // Check if all required parameters are set.
        if (message.getShortMessage() == null || message.getShortMessage().length() == 0 || message.getHost() == null || message.getHost().length() == 0) {
            throw new Exception("Missing GELF message parameters. short_message and host are required.");
        }
        DBCollection coll = this.getMessagesColl();

        BasicDBObject dbObj = new BasicDBObject();

        dbObj.put("gelf", true);
        dbObj.put("message", message.getShortMessage());
        dbObj.put("full_message", message.getFullMessage());
        dbObj.put("file", message.getFile());
        dbObj.put("line", message.getLine());
        dbObj.put("host", message.getHost());
        dbObj.put("facility", null);
        dbObj.put("level", message.getLevel());

        // Add additional fields.
        Map<String,String> additionalFields = message.getAdditionalData();
        Set<String> set = additionalFields.keySet();
        Iterator<String> iter = set.iterator();
        while(iter.hasNext()) {
            String key = iter.next();
            String value = additionalFields.get(key);
            dbObj.put(key, value);
        }

        dbObj.put("created_at", (int) (System.currentTimeMillis()/1000));
        // Documents in capped collections cannot grow so we have to do that now and cannot just add 'deleted => true' later.
        dbObj.put("deleted", false);

        coll.insert(dbObj);
    }