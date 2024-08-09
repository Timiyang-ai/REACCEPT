private static void updateFields(Contract contract, HashMap<String, String> fields) throws IOException {

        for (String fieldName : fields.keySet()) {
            report("update field: " + fieldName + " -> " + fields.get(fieldName));

            Binder binder = new Binder();
            Binder data = null;

            try {
                XStream xstream = new XStream(new DomDriver());
                xstream.registerConverter(new MapEntryConverter());
                xstream.alias(fieldName, Binder.class);
                data = Binder.convertAllMapsToBinders(xstream.fromXML(fields.get(fieldName)));
            } catch (Exception xmlEx) {
//                xmlEx.printStackTrace();
                try {
                    Gson gson = new GsonBuilder().create();
                    binder = Binder.convertAllMapsToBinders(gson.fromJson(fields.get(fieldName), Binder.class));
                    data = (Binder) data.get(fieldName);
                } catch (Exception jsonEx) {
//                    jsonEx.printStackTrace();
                    try {
                        Yaml yaml = new Yaml();
                        data = Binder.convertAllMapsToBinders(yaml.load(fields.get(fieldName)));
                        data = (Binder) data.get(fieldName);
                    } catch (Exception yamlEx) {
                        yamlEx.printStackTrace();
                    }
                }
            }

            if (data != null) {
                BiDeserializer bm = DefaultBiMapper.getInstance().newDeserializer();

                binder.put("data", bm.deserialize(data));

                contract.set(fieldName, binder);

                report("update field " + fieldName + " ok");
            } else {
                report("update field " + fieldName + " error: no valid data");
            }
        }

        report("contract expires at " + DateTimeFormatter.ofPattern("yyyy-MM-dd").format(contract.getExpiresAt()));
    }