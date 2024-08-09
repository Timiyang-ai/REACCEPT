@RequestMapping("")
	public String runScript(@RequestParam(value = "script", required = false) String script, Model model) {
		if (StringUtils.isNotBlank(script)) {
			String result = processPython(script);
			model.addAttribute("script", script);
			model.addAttribute("result", result);
		}
		return "operation/scriptConsole";
	}