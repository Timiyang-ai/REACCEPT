@RequestMapping("")
	public String runScript(@RequestParam(value = "script", required = false) String script, Model model) {
		if (interp == null) {
			model.addAttribute("script", script);
			model.addAttribute("result", "Scrpt Console is disabled due to memory config."
							+ " Please set up Perm Gen memory more than 200M");
		} else if (StringUtils.isNotBlank(script)) {
			String result = processPython(script);
			model.addAttribute("script", script);
			model.addAttribute("result", result);
		}
		return "operation/script_console";
	}