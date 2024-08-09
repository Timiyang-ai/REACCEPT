public void insertGelfMessage(GELFMessage message) throws Exception {
        // Check if all required parameters are set.
        if (message.shortMessage == null || message.shortMessage.length() == 0 || message.host == null || message.host.length() == 0) {
            throw new Exception("Missing GELF message parameters. short_message and host are required.");
        }
        DBCollection coll = this.getMessagesColl();

        BasicDBObject dbObj = new BasicDBObject();

        dbObj.put("gelf", true);
        dbObj.put("message", message.shortMessage);
        dbObj.put("full_message", message.fullMessage);
        dbObj.put("type", message.type);
        dbObj.put("file", message.file);
        dbObj.put("line", message.line);
        dbObj.put("host", message.host);
        dbObj.put("facility", null);
        dbObj.put("level", message.level);
        dbObj.put("created_at", (int) (System.currentTimeMillis()/1000));
        // Documents in capped collections cannot grow so we have to do that now and cannot just add 'deleted => true' later.
        dbObj.put("deleted", false);

        coll.insert(dbObj);
    }