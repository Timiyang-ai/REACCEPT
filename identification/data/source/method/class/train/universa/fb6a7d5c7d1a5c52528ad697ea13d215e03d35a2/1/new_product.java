private static void updateFields(Contract contract, HashMap<String, String> fields) throws IOException {

        for (String fieldName : fields.keySet()) {
            report("update field: " + fieldName + " -> " + fields.get(fieldName));

            Binder data = null;
            Object obj = null;

            try {
                XStream xstream = new XStream(new DomDriver());
                xstream.registerConverter(new MapEntryConverter());
                xstream.alias(fieldName, Binder.class);
                data = Binder.convertAllMapsToBinders(xstream.fromXML(fields.get(fieldName)));
            } catch (Exception xmlEx) {
                try {
                    Gson gson = new GsonBuilder().create();
                    data = Binder.convertAllMapsToBinders(gson.fromJson(fields.get(fieldName), Binder.class));
                    if (data.containsKey(fieldName))
                        data = (Binder) data.get(fieldName);
                } catch (Exception jsonEx) {
                    try {
                        Yaml yaml = new Yaml();
                        Object loaded = Binder.convertAllMapsToBinders(yaml.load(fields.get(fieldName)));
                        if (loaded.getClass().equals(Binder.class)) {
                            data = (Binder) loaded;
                            if (data.containsKey(fieldName))
                                data = (Binder) data.get(fieldName);
                        } else
                            obj = loaded;
                    } catch (Exception yamlEx) {
                        try {
                            obj = fields.get(fieldName);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            xmlEx.printStackTrace();
                            jsonEx.printStackTrace();
                            yamlEx.printStackTrace();
                        }
                    }
                }
            }

            if ((data != null) || (obj != null)) {
                Binder binder = new Binder();

                if (data != null) {
                    BiDeserializer bm = DefaultBiMapper.getInstance().newDeserializer();
                    binder.put("data", bm.deserialize(data));
                }
                else
                    binder.put("data", obj);

                contract.set(fieldName, binder);

                report("update field " + fieldName + " ok");
            } else {
                report("update field " + fieldName + " error: no valid data");
            }
        }

        report("contract expires at " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(contract.getExpiresAt()));
    }