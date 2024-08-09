@RequestMapping(method = RequestMethod.POST, value = "/gateways/senders", params = "op=start")
  @ResponseBody
  public String startGatewaySender(
      @RequestParam(CliStrings.START_GATEWAYSENDER__ID) final String gatewaySenderId,
      @RequestParam(value = CliStrings.GROUP, required = false) final String[] groups,
      @RequestParam(value = CliStrings.MEMBER, required = false) final String[] members) {
    CommandStringBuilder command = new CommandStringBuilder(CliStrings.START_GATEWAYSENDER);

    command.addOption(CliStrings.START_GATEWAYSENDER__ID, gatewaySenderId);

    if (hasValue(groups)) {
      command.addOption(CliStrings.GROUP, StringUtils.join(groups, StringUtils.COMMA_DELIMITER));
    }

    if (hasValue(members)) {
      command.addOption(CliStrings.MEMBER, StringUtils.join(members, StringUtils.COMMA_DELIMITER));
    }

    return processCommand(command.toString());
  }