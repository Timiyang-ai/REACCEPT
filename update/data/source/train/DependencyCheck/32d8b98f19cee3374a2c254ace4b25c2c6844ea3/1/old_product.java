public void parse(File file) throws UpdateException {
        LOGGER.debug("Parsing " + file.getName());
        try (InputStream fin = new FileInputStream(file);
                InputStream in = new GZIPInputStream(fin);
                InputStreamReader isr = new InputStreamReader(in, UTF_8);
                JsonReader reader = new JsonReader(isr)) {
            final Gson gson = new GsonBuilder().create();

            reader.beginObject();

            while (reader.hasNext() && !JsonToken.BEGIN_ARRAY.equals(reader.peek())) {
                reader.skipValue();
            }
            reader.beginArray();
            while (reader.hasNext()) {
                final CVEItem cve = gson.fromJson(reader, CVEItem.class);

                //cve.getCve().getCVEDataMeta().getSTATE();
                if (testCveCpeStartWithFilter(cve)) {
                    cveDB.updateVulnerability(cve);
                }
            }
        } catch (FileNotFoundException ex) {
            LOGGER.error(ex.getMessage());
            throw new UpdateException("Unable to find the NVD CPE file, `" + file + "`, to parse", ex);
        } catch (IOException ex) {
            LOGGER.error("Error reading NVD JSON data: {}", file);
            LOGGER.debug("Error extracting the NVD JSON data from: " + file.toString(), ex);
            throw new UpdateException("Unable to find the NVD CPE file to parse", ex);
        }
    }