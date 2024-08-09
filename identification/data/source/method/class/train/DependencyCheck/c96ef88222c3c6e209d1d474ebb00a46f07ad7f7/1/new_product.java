protected void processTemplate(String templateName, OutputStream outputStream) throws ReportException {
        InputStream input = null;
        String logTag = null;
        final File f = new File(templateName);
        try {
            if (f.isFile()) {
                try {
                    logTag = templateName;
                    input = new FileInputStream(f);
                } catch (FileNotFoundException ex) {
                    throw new ReportException("Unable to locate template file: " + templateName, ex);
                }
            } else {
                logTag = "templates/" + templateName + ".vsl";
                input = this.getClass().getClassLoader().getResourceAsStream(logTag);
            }
            if (input == null) {
                logTag = templateName;
                input = this.getClass().getClassLoader().getResourceAsStream(templateName);
            }
            if (input == null) {
                throw new ReportException("Template file doesn't exist: " + logTag);
            }

            try (InputStreamReader reader = new InputStreamReader(input, "UTF-8");
                    OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8")) {
                if (!velocityEngine.evaluate(context, writer, logTag, reader)) {
                    throw new ReportException("Failed to convert the template into html.");
                }
                writer.flush();
            } catch (UnsupportedEncodingException ex) {
                throw new ReportException("Unable to generate the report using UTF-8", ex);
            } catch (IOException ex) {
                throw new ReportException("Unable to write the report", ex);
            }
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    LOGGER.trace("Error closing input", ex);
                }
            }
        }
    }