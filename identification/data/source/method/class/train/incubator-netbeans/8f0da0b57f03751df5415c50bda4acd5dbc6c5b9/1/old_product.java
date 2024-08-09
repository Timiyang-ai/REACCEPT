public static URL generateHtmlFileURL(FileObject appletFile, FileObject buildDir, FileObject classesDir, String activePlatform)
                                   throws FileStateInvalidException {
        FileObject html = null;
        IOException ex = null;

        if ((appletFile == null) || (buildDir == null) || (classesDir == null)) {
            return null;
        }

        try {
            html = generateHtml(appletFile, buildDir, classesDir);
        } catch (IOException iex) {
            ex = iex;
        }

        URL url = null;

        try {
            if (ex == null) {
                // JDK issue #6193279: Appletviewer does not accept encoded URLs
                JavaPlatformManager pm = JavaPlatformManager.getDefault();
                JavaPlatform platform = null;

                if (activePlatform == null) {
                    platform = pm.getDefaultPlatform();
                } else {
                    JavaPlatform[] installedPlatforms = pm.getPlatforms(null, new Specification("j2se", null)); //NOI18N

                    for (int i = 0; i < installedPlatforms.length; i++) {
                        String antName = (String) installedPlatforms[i].getProperties().get("platform.ant.name"); //NOI18N

                        if ((antName != null) && antName.equals(activePlatform)) {
                            platform = installedPlatforms[i];

                            break;
                        }
                    }
                }

                boolean workAround6193279 = (platform != null //In case of nonexisting platform don't use the workaround
                ) && (platform.getSpecification().getVersion().compareTo(JDK_15) >= 0); //JDK1.5 and higher

                if (workAround6193279) {
                    File f = FileUtil.toFile(html);

                    try {
                        url = new URL("file", null, f.getAbsolutePath()); // NOI18N
                    } catch (MalformedURLException e) {
                        ErrorManager.getDefault().notify(e);
                    }
                } else {
                    url = html.getURL();
                }
            }
        } catch (FileStateInvalidException f) {
            throw new FileStateInvalidException();
        }

        return url;
    }