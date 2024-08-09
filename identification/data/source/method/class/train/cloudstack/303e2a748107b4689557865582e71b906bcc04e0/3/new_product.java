public static void pidCheck(String pidDir, String run) throws ConfigurationException {
		
		String dir = pidDir==null?"/var/run":pidDir;
		
		try {
			final File propsFile = PropertiesUtil.findConfigFile("environment.properties");
			if (propsFile == null) {
				s_logger.debug("environment.properties could not be opened");
			}
			else {
				final FileInputStream finputstream = new FileInputStream(propsFile);
				final Properties props = new Properties();
				props.load(finputstream);
				finputstream.close();
				dir = props.getProperty("paths.pid");
				if (dir == null) {
					dir = "/var/run";
				}
			}
		} catch (IOException e) {
			s_logger.debug("environment.properties could not be opened");
		}
		
	    final File pidFile = new File(dir + File.separator + run);
	    try {
	        if (!pidFile.createNewFile()) {
	            if (!pidFile.exists()) {
	                throw new ConfigurationException("Unable to write to " + pidFile.getAbsolutePath() + ".  Are you sure you're running as root?");
	            }
	
	            final FileInputStream is = new FileInputStream(pidFile);
	            final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	            final String pidLine = reader.readLine();
	            if (pidLine == null) {
	                throw new ConfigurationException("Java process is being started twice.  If this is not true, remove " + pidFile.getAbsolutePath());
	            }
	            try {
	                final long pid = Long.parseLong(pidLine);
	                final Script script = new Script("bash", 120000, s_logger);
	                script.add("-c", "ps -p " + pid);
	                final String result = script.execute();
	                if (result == null) {
	                    throw new ConfigurationException("Java process is being started twice.  If this is not true, remove " + pidFile.getAbsolutePath());
	                }
	                if (!pidFile.delete()) {
	                    throw new ConfigurationException("Java process is being started twice.  If this is not true, remove " + pidFile.getAbsolutePath());
	                }
	                if (!pidFile.createNewFile()) {
	                    throw new ConfigurationException("Java process is being started twice.  If this is not true, remove " + pidFile.getAbsolutePath());
	                }
	            } catch (final NumberFormatException e) {
	                throw new ConfigurationException("Java process is being started twice.  If this is not true, remove " + pidFile.getAbsolutePath());
	            }
	        }
	        pidFile.deleteOnExit();
	
	        final Script script = new Script("bash", 120000, s_logger);
	        script.add("-c", "echo $PPID");
	        final OutputInterpreter.OneLineParser parser = new OutputInterpreter.OneLineParser();
	        script.execute(parser);
	
	        final String pid = parser.getLine();
	
	        final FileOutputStream strm = new FileOutputStream(pidFile);
	        strm.write((pid + "\n").getBytes());
	        strm.close();
	    } catch (final IOException e) {
	        throw new CloudRuntimeException("Unable to create the " + pidFile.getAbsolutePath() + ".  Are you running as root?", e);
	    }
	}