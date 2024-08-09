@Override
    public void analyzeDependency(Dependency dependency, Engine engine) throws AnalysisException {
        final File test = new File(dependency.getActualFilePath());
        if (!test.isFile()) {
            throw new AnalysisException(String.format("%s does not exist and cannot be analyzed by dependency-check",
                    dependency.getActualFilePath()));
        }
        if (grokAssemblyExe == null) {
            LOGGER.warn("GrokAssembly didn't get deployed");
            return;
        }
        final List<String> args = buildArgumentList();
        if (args == null) {
            LOGGER.warn("Assembly Analyzer was unable to execute");
            return;
        }
        args.add(dependency.getActualFilePath());
        final ProcessBuilder pb = new ProcessBuilder(args);
        Document doc = null;
        try {
            final Process proc = pb.start();
            final DocumentBuilder builder = XmlUtils.buildSecureDocumentBuilder();

            doc = builder.parse(proc.getInputStream());

            // Try evacuating the error stream
            final String errorStream = IOUtils.toString(proc.getErrorStream(), "UTF-8");
            if (null != errorStream && !errorStream.isEmpty()) {
                LOGGER.warn("Error from GrokAssembly: {}", errorStream);
            }

            int rc = 0;
            try {
                rc = proc.waitFor();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                return;
            }
            if (rc == 3) {
                LOGGER.debug("{} is not a .NET assembly or executable and as such cannot be analyzed by dependency-check",
                        dependency.getActualFilePath());
                return;
            } else if (rc != 0) {
                LOGGER.debug("Return code {} from GrokAssembly; dependency-check is unable to analyze the library: {}",
                        rc, dependency.getActualFilePath());
                return;
            }

            final XPath xpath = XPathFactory.newInstance().newXPath();

            // First, see if there was an error
            final String error = xpath.evaluate("/assembly/error", doc);
            if (error != null && !error.isEmpty()) {
                throw new AnalysisException(error);
            }

            final String version = xpath.evaluate("/assembly/version", doc);
            if (version != null) {
                dependency.getVersionEvidence().addEvidence(new Evidence("grokassembly", "version",
                        version, Confidence.HIGHEST));
            }

            final String vendor = xpath.evaluate("/assembly/company", doc);
            if (vendor != null) {
                dependency.getVendorEvidence().addEvidence(new Evidence("grokassembly", "vendor",
                        vendor, Confidence.HIGH));
            }

            final String product = xpath.evaluate("/assembly/product", doc);
            if (product != null) {
                dependency.getProductEvidence().addEvidence(new Evidence("grokassembly", "product",
                        product, Confidence.HIGH));
            }

        } catch (ParserConfigurationException pce) {
            throw new AnalysisException("Error initializing the assembly analyzer", pce);
        } catch (IOException | XPathExpressionException ioe) {
            throw new AnalysisException(ioe);
        } catch (SAXException saxe) {
            LOGGER.error("----------------------------------------------------");
            LOGGER.error("Failed to read the Assembly Analyzer results. "
                    + "On some systems mono-runtime and mono-devel need to be installed.");
            LOGGER.error("----------------------------------------------------");
            throw new AnalysisException("Couldn't parse Assembly Analyzer results (GrokAssembly)", saxe);
        }
    }