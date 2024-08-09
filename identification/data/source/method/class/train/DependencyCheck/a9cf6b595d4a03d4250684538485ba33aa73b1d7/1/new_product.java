public Dependency insepct(File file) throws IOException {

        Dependency dependency = new Dependency();

        String fileName = file.getName();
        dependency.setFileName(fileName);
        dependency.setFilePath(file.getCanonicalPath());

        //slightly process the filename to chunk it into distinct words, numbers.
        // Yes, the lucene analyzer might do this, but I want a little better control
        // over the process.
        String fileNameEvidence = fileName.substring(0, fileName.length() - 4).toLowerCase().replace('-', ' ').replace('_', ' ');
        StringBuilder sb = new StringBuilder(fileNameEvidence.length());
        STRING_STATE state = determineState(fileNameEvidence.charAt(0));

        for (int i = 0; i < fileNameEvidence.length(); i++) {
            char c = fileNameEvidence.charAt(i);
            STRING_STATE newState = determineState(c);
            if (newState != state) {
                if ((state != STRING_STATE.NUMBER && newState == STRING_STATE.PERIOD)
                        || (state == STRING_STATE.PERIOD && newState != STRING_STATE.NUMBER)
                        || (state == STRING_STATE.ALPHA || newState == STRING_STATE.ALPHA)
                        || ((state == STRING_STATE.OTHER || newState == STRING_STATE.OTHER) && c != ' ')) {
                    sb.append(' ');
                }
            }
            state = newState;
            sb.append(c);
        }
        Pattern rx = Pattern.compile("\\s\\s+");
        fileNameEvidence = rx.matcher(sb.toString()).replaceAll(" ");
        dependency.getProductEvidence().addEvidence("jar", "file name",
                fileNameEvidence, Evidence.Confidence.HIGH);
        dependency.getVendorEvidence().addEvidence("jar", "file name",
                fileNameEvidence, Evidence.Confidence.HIGH);
        if (fileNameEvidence.matches(".*\\d.*")) {
            dependency.getVersionEvidence().addEvidence("jar", "file name",
                    fileNameEvidence, Evidence.Confidence.HIGH);
        }
        String md5 = null;
        String sha1 = null;
        try {
            md5 = Checksum.getMD5Checksum(file);
            sha1 = Checksum.getSHA1Checksum(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JarAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(JarAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
        dependency.setMd5sum(md5);
        dependency.setSha1sum(sha1);

        parseManifest(dependency);
        analyzePackageNames(dependency);

        return dependency;
    }