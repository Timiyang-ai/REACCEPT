public static boolean startModule(Module mod, ServletContext servletContext, boolean delayContextRefresh) {
		//register the module loggers
		try {
			if (mod.getLog4j() != null) {
				DOMConfigurator.configure(mod.getLog4j().getDocumentElement());
			}
		}
		catch (Exception e) {
			log.warn("Unable to load module loggers", e);
		}
		
		if (log.isDebugEnabled())
			log.debug("trying to start module " + mod);
		
		// only try and start this module if the api started it without a
		// problem.
		if (ModuleFactory.isModuleStarted(mod) && !mod.hasStartupError()) {
			
			String realPath = servletContext.getRealPath("");
			
			// copy the messages into the webapp
			String path = "/WEB-INF/module_messages@LANG@.properties";
			
			for (Entry<String, Properties> entry : mod.getMessages().entrySet()) {
				if (log.isDebugEnabled())
					log.debug("Copying message property file: " + entry.getKey());
				
				String lang = "_" + entry.getKey();
				if (lang.equals("_en") || lang.equals("_"))
					lang = "";
				
				String currentPath = path.replace("@LANG@", lang);
				
				String absolutePath = realPath + currentPath;
				File file = new File(absolutePath);
				try {
					if (!file.exists())
						file.createNewFile();
				}
				catch (IOException ioe) {
					log.error("Unable to create new file " + file.getAbsolutePath() + " " + ioe);
				}
				
				Properties props = entry.getValue();
				
				// set all properties to start with 'moduleName.' if not already
				List<Object> keys = new Vector<Object>();
				keys.addAll(props.keySet());
				for (Object obj : keys) {
					String key = (String) obj;
					if (!key.startsWith(mod.getModuleId())) {
						props.put(mod.getModuleId() + "." + key, props.get(key));
						props.remove(key);
					}
				}
				
				// append the properties to the appropriate messages file
				OpenmrsUtil.storeProperties(props, file, "Module: " + mod.getName() + " v" + mod.getVersion());
			}
			log.debug("Done copying messages");
			
			// flag to tell whether we added any xml/dwr/etc changes that necessitate a refresh
			// of the web application context
			boolean moduleNeedsContextRefresh = false;
			
			// copy the html files into the webapp (from /web/module/ in the module)
			// also looks for a spring context file. If found, schedules spring to be restarted
			JarFile jarFile = null;
			try {
				File modFile = mod.getFile();
				jarFile = new JarFile(modFile);
				Enumeration<JarEntry> entries = jarFile.entries();
				
				while (entries.hasMoreElements()) {
					JarEntry entry = entries.nextElement();
					String name = entry.getName();
					log.debug("Entry name: " + name);
					if (name.startsWith("web/module/")) {
						// trim out the starting path of "web/module/"
						String filepath = name.substring(11);
						
						StringBuffer absPath = new StringBuffer(realPath + "/WEB-INF");
						
						// If this is within the tag file directory, copy it into /WEB-INF/tags/module/moduleId/...
						if (filepath.startsWith("tags/")) {
							filepath = filepath.substring(5);
							absPath.append("/tags/module/");
						}
						// Otherwise, copy it into /WEB-INF/view/module/moduleId/...
						else {
							absPath.append("/view/module/");
						}
						
						// if a module id has a . in it, we should treat that as a /, i.e. files in the module
						// ui.springmvc should go in folder names like .../ui/springmvc/...
						absPath.append(mod.getModuleIdAsPath() + "/" + filepath);
						if (log.isDebugEnabled())
							log.debug("Moving file from: " + name + " to " + absPath);
						
						// get the output file
						File outFile = new File(absPath.toString().replace("/", File.separator));
						if (entry.isDirectory()) {
							if (!outFile.exists()) {
								outFile.mkdirs();
							}
						} else {
							// make the parent directories in case it doesn't exist
							File parentDir = outFile.getParentFile();
							if (!parentDir.exists()) {
								parentDir.mkdirs();
							}
							
							//if (outFile.getName().endsWith(".jsp") == false)
							//	outFile = new File(absPath.replace("/", File.separator) + MODULE_NON_JSP_EXTENSION);
							
							// copy the contents over to the webapp for non directories
							OutputStream outStream = new FileOutputStream(outFile, false);
							InputStream inStream = jarFile.getInputStream(entry);
							OpenmrsUtil.copyFile(inStream, outStream);
							inStream.close();
							outStream.close();
						}
					} else if (name.equals("moduleApplicationContext.xml") || name.equals("webModuleApplicationContext.xml")) {
						moduleNeedsContextRefresh = true;
					} else if (name.equals(mod.getModuleId() + "Context.xml")) {
						String msg = "DEPRECATED: '" + name
						        + "' should be named 'moduleApplicationContext.xml' now. Please update/upgrade. ";
						throw new ModuleException(msg, mod.getModuleId());
					}
				}
			}
			catch (IOException io) {
				log.warn("Unable to copy files from module " + mod.getModuleId() + " to the web layer", io);
			}
			finally {
				if (jarFile != null) {
					try {
						jarFile.close();
					}
					catch (IOException io) {
						log.warn("Couldn't close jar file: " + jarFile.getName(), io);
					}
				}
			}
			
			// find and add the dwr code to the dwr-modules.xml file (if defined)
			InputStream inputStream = null;
			try {
				Document config = mod.getConfig();
				Element root = config.getDocumentElement();
				if (root.getElementsByTagName("dwr").getLength() > 0) {
					
					// get the dwr-module.xml file that we're appending our code to
					File f = new File(realPath + "/WEB-INF/dwr-modules.xml".replace("/", File.separator));
					inputStream = new FileInputStream(f);
					Document dwrmodulexml = getDWRModuleXML(inputStream, realPath);
					Element outputRoot = dwrmodulexml.getDocumentElement();
					
					// loop over all of the children of the "dwr" tag
					Node node = root.getElementsByTagName("dwr").item(0);
					Node current = node.getFirstChild();
					while (current != null) {
						if ("allow".equals(current.getNodeName()) || "signatures".equals(current.getNodeName())) {
							((Element) current).setAttribute("moduleId", mod.getModuleId());
							outputRoot.appendChild(dwrmodulexml.importNode(current, true));
						}
						
						current = current.getNextSibling();
					}
					
					moduleNeedsContextRefresh = true;
					
					// save the dwr-modules.xml file.
					OpenmrsUtil.saveDocument(dwrmodulexml, f);
				}
			}
			catch (FileNotFoundException e) {
				throw new ModuleException(realPath + "/WEB-INF/dwr-modules.xml file doesn't exist.", e);
			}
			finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					}
					catch (IOException io) {
						log.error("Error while closing input stream", io);
					}
				}
			}
			
			// mark to delete the entire module web directory on exit
			// this will usually only be used when an improper shutdown has occurred.
			String folderPath = realPath + "/WEB-INF/view/module/" + mod.getModuleIdAsPath();
			File outFile = new File(folderPath.replace("/", File.separator));
			outFile.deleteOnExit();
			
			// additional checks on module needing a context refresh
			if (moduleNeedsContextRefresh == false) {
				
				// AOP advice points are only loaded during the context refresh now.
				// if the context hasn't been marked to be refreshed yet, mark it
				// now if this module defines some advice
				if (mod.getAdvicePoints() != null && mod.getAdvicePoints().size() > 0) {
					moduleNeedsContextRefresh = true;
				}
				
			}
			
			// refresh the spring web context to get the just-created xml
			// files into it (if we copied an xml file)
			if (moduleNeedsContextRefresh && delayContextRefresh == false) {
				if (log.isDebugEnabled())
					log.debug("Refreshing context for module" + mod);
				
				try {
					refreshWAC(servletContext, false, mod);
					log.debug("Done Refreshing WAC");
				}
				catch (Exception e) {
					String msg = "Unable to refresh the WebApplicationContext";
					mod.setStartupErrorMessage(msg, e);
					
					if (log.isWarnEnabled())
						log.warn(msg + " for module: " + mod.getModuleId(), e);
					
					try {
						ModuleFactory.stopModule(mod, true, true); //remove jar from classloader play
						stopModule(mod, servletContext, true);
					}
					catch (Exception e2) {
						// exception expected with most modules here
						if (log.isWarnEnabled())
							log.warn("Error while stopping a module that had an error on refreshWAC", e2);
					}
					
					// try starting the application context again
					refreshWAC(servletContext, false, mod);
				}
				
			}
			
			if (!delayContextRefresh) {
				// only loading the servlets/filters if spring is refreshed because one
				// might depend on files being available in spring
				// if the caller wanted to delay the refresh then they are responsible for
				// calling these two methods on the module

				// find and cache the module's servlets
				//(only if the module started successfully previously)
				if (ModuleFactory.isModuleStarted(mod)) {
					log.debug("Loading servlets and filters for module: " + mod);
					loadServlets(mod, servletContext);
					loadFilters(mod, servletContext);
				}
			}
			
			// return true if the module needs a context refresh and we didn't do it here
			return (moduleNeedsContextRefresh && delayContextRefresh == true);
			
		}
		
		// we aren't processing this module, so a context refresh is not necessary
		return false;
	}