@RequestMapping(method = RequestMethod.POST, value = "/gateways/senders/{id}",
      params = "op=pause")
  @ResponseBody
  public String pauseGatewaySender(@PathVariable("id") final String gatewaySenderId,
      @RequestParam(value = CliStrings.GROUP, required = false) final String[] groups,
      @RequestParam(value = CliStrings.MEMBER, required = false) final String[] members) {
    CommandStringBuilder command = new CommandStringBuilder(CliStrings.PAUSE_GATEWAYSENDER);

    command.addOption(CliStrings.PAUSE_GATEWAYSENDER__ID, decode(gatewaySenderId));

    if (hasValue(groups)) {
      command.addOption(CliStrings.GROUP, StringUtils.join(groups, StringUtils.COMMA_DELIMITER));
    }

    if (hasValue(members)) {
      command.addOption(CliStrings.MEMBER, StringUtils.join(members, StringUtils.COMMA_DELIMITER));
    }

    return processCommand(command.toString());
  }