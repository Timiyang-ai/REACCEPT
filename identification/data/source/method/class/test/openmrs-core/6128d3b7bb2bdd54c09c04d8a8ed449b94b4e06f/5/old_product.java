public static void startModule(Module mod, ServletContext servletContext) {
		
		// only try and start this module if the api started it without a problem.
		log.debug("trying to start " + mod);
		if (ModuleFactory.isModuleStarted(mod) && !mod.hasStartupError()) {
			
			String realPath = servletContext.getRealPath("");
			
			// copy the messages into the webapp
			String path = "/WEB-INF/module_messages@LANG@.properties";
			
			for (Entry<String, Properties> entry : mod.getMessages().entrySet()) {
				log.debug("Copying message property file: " + entry.getKey());
				
				String lang = "_" + entry.getKey();
				if (lang.equals("_en") || lang.equals("_"))
					lang = "";
				
				String currentPath = path.replace("@LANG@", lang);
				
				OutputStream outStream = null;
				try {
					String absolutePath = realPath + currentPath;
					File file = new File(absolutePath);
					
					if (!file.exists())
						file.createNewFile();
					
					outStream = new FileOutputStream(file, true);
					
					Properties props = entry.getValue();
					
					// set all properties to start with 'moduleName.' if not already
					List<Object> keys = new Vector<Object>();
					keys.addAll(props.keySet());
					for (Object obj : keys) {
						String key = (String)obj;
						if (!key.startsWith(mod.getModuleId())) {
							props.put(mod.getModuleId() + "." + key, props.get(key));
							props.remove(key);
						}
					}
					
					// append the properties to the appropriate messages file
					props.store(outStream, "Module: " + mod.getName() + " v" + mod.getVersion());
				}
				catch (IOException e) {
					log.error("Unable to load in lang: '" + entry.getKey() + "' properties for mod: " + mod.getName(), e);
				}
				finally {
					if (outStream != null) {
						try {
							outStream.close();
						}
						catch (IOException e) {
							log.warn("Couldn't close outStream", e);
						}
					}
				}
				
			}
			log.debug("Done copying messages");
			
			// flag to tell whether we added any xml/dwr/etc changes that necessitate a refresh
			// of the web application context
			boolean refreshContext = false;
			
			// copy the spring xml file and html files into the webapp (from /web/module/ in the module)
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
						String absPath = realPath + "/WEB-INF/view/module/" + mod.getModuleId() + "/" + filepath;
						// get the output file
						File outFile = new File(absPath.replace("/", File.separator));
						if (!outFile.exists() && entry.isDirectory())
							outFile.mkdirs();
						else {
							//if (outFile.getName().endsWith(".jsp") == false)
							//	outFile = new File(absPath.replace("/", File.separator) + MODULE_NON_JSP_EXTENSION);
							
							// copy the contents over to the webpp for non directories
							OutputStream outStream = new FileOutputStream(outFile, false);
							InputStream inStream = jarFile.getInputStream(entry);
							OpenmrsUtil.copyFile(inStream, outStream);
							inStream.close();
							outStream.close();
						}
					}
					else if (name.equals("moduleApplicationContext.xml")) {
						refreshContext = true;
					}
					else if (name.equals(mod.getModuleId() + "Context.xml")) {
						String msg = "DEPRECATED: '" + name + "' should be named 'moduleApplicationContext.xml' now. Please update/upgrade. ";
						mod.setStartupErrorMessage(msg);
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
						if ("allow".equals(current.getNodeName()) ||
							"signatures".equals(current.getNodeName())) {
								((Element)current).setAttribute("moduleId", mod.getModuleId());
								outputRoot.appendChild(dwrmodulexml.importNode(current, true));
						}
						
						current = current.getNextSibling();
					}
					
					refreshContext = true;
					
					// save the dwr-modules.xml file.
					OpenmrsUtil.saveDocument(dwrmodulexml, f);
				}
			}
			catch (FileNotFoundException e) {
				throw new ModuleException("/WEB-INF/dwr-modules.xml file doesn't exist.", e);
			}
			finally  {
				if (inputStream != null) {
					try {
						inputStream.close();					
					}
					catch (IOException io) {
						log.error("Error while closing input stream", io);
					}
				}
			}
			
			// refresh the spring web context to get the just-created xml 
			// files into it (if we copied an xml file)
			if (refreshContext) {
				try {
					
					// must "refresh" the spring dispatcherservlet as well to add in 
					//the new handlerMappings
					if (dispatcherServlet != null)
						dispatcherServlet.reInitFrameworkServlet();
						
					refreshWAC(servletContext);
					log.debug("Done Refreshing WAC");
					
					if (dwrServlet != null)
						dwrServlet.reInitServlet();
					
				}
				catch (Exception e) {
					String msg = "Unable to refresh the WebApplicationContext"; 
					log.warn(msg + " for module: " + mod.getModuleId(), e);
					mod.setStartupErrorMessage(msg + " : " + e.getMessage());
					
					try {
						stopModule(mod, servletContext, true);
					}
					catch (Exception e2) {
						// exception expected with most modules here
						log.warn("Error while stopping a module that had an error on refreshWAC", e);
					}
					System.gc();
					
					// try starting the application context again
					refreshWAC(servletContext);
				}
				
				// reload the advice points that were lost when refreshing Spring
				log.debug("Reloading advice for all started modules: " + ModuleFactory.getStartedModules().size());
				for (Module module : ModuleFactory.getStartedModules()) {
					ModuleFactory.loadAdvice(module);
				}
			}
			
			// mark to delete the entire module web directory on exit 
			// this will usually only be used when an improper shutdown has occurred.
			String folderPath = realPath + "/WEB-INF/view/module/" + mod.getModuleId();
			File outFile = new File(folderPath.replace("/", File.separator));
			outFile.deleteOnExit();
			
			
			// find and cache the module's servlets
			Element rootNode = mod.getConfig().getDocumentElement();
			NodeList servletTags = rootNode.getElementsByTagName("servlet");
			Map<String, HttpServlet> servletMap = new HashMap<String, HttpServlet>();
			
			for (int i=0; i< servletTags.getLength(); i++) {
				Node node = servletTags.item(i);
				NodeList childNodes = node.getChildNodes();
				String name = "", className = "";
				for (int j=0; j < childNodes.getLength(); j++) {
					Node childNode = childNodes.item(j);
					if ("servlet-name".equals(childNode.getNodeName()))
						name = childNode.getTextContent();
					else if("servlet-class".equals(childNode.getNodeName()))
						className = childNode.getTextContent();
				}
				if (name.length() == 0 || className.length() == 0) {
					log.warn("both 'servlet-name' and 'servlet-class' are required for the 'servlet' tag. Given '" + name + "' and '" + className + "' for module " + mod.getName());
					continue;
				}
				
				
				HttpServlet httpServlet = null;
				try {
					httpServlet = (HttpServlet)ModuleFactory.getModuleClassLoader(mod).loadClass(className).newInstance();
				}
				catch (ClassNotFoundException e) {
					log.warn("Class not found for servlet " + name + " for module " + mod.getName(), e);
					continue;
				}
				catch (IllegalAccessException e) {
					log.warn("Class cannot be accessed for servlet " + name + " for module " + mod.getName(), e);
					continue;
				}
				catch (InstantiationException e) {
					log.warn("Class cannot be instantiated for servlet " + name + " for module " + mod.getName(), e);
					continue;
				}
				
				servletMap.put(name, httpServlet);
			}
			moduleServlets.put(mod, servletMap);
		}
		
	}