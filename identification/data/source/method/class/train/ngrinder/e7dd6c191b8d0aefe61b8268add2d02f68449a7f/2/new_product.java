@RequestMapping("")
	public String run(@RequestParam(value = "script", defaultValue = "") String script, Model model) {
		if (StringUtils.isNotBlank(script)) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("Groovy");
			engine.put("applicationContext", this.applicationContext);
			engine.put("agentManager", this.agentManager);
			engine.put("agentManagerService", this.agentManagerService);
			engine.put("regionService", this.regionService);
			engine.put("consoleManager", this.consoleManager);
			engine.put("userService", this.userService);
			engine.put("perfTestService", this.perfTestService);
			engine.put("tagService", this.tagService);
			engine.put("fileEntryService", this.fileEntryService);
			engine.put("config", getConfig());
			engine.put("pluginManager", this.pluginManager);
			engine.put("cacheManager", this.cacheManager);

			final StringWriter out = new StringWriter();
			PrintWriter writer = new PrintWriter(out);
			engine.getContext().setWriter(writer);
			engine.getContext().setErrorWriter(writer);
			try {
				Object result = engine.eval(script);
				result = out.toString() + "\n" + ObjectUtils.defaultIfNull(result, "");
				model.addAttribute("result", result);
			} catch (ScriptException e) {
				model.addAttribute("result", out.toString() + "\n" + e.getMessage());
			}
		}
		model.addAttribute("script", script);
		return "operation/script_console";
	}