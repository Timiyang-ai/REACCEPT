@RequestMapping(method = RequestMethod.POST, value = "/gateways/senders", params = "op=start")
  @ResponseBody
  public String startGatewaySender(
      @RequestParam(CliStrings.START_GATEWAYSENDER__ID) final String gatewaySenderId,
      @RequestParam(value = CliStrings.START_GATEWAYSENDER__GROUP,
          required = false) final String[] groups,
      @RequestParam(value = CliStrings.START_GATEWAYSENDER__MEMBER,
          required = false) final String[] members) {
    CommandStringBuilder command = new CommandStringBuilder(CliStrings.START_GATEWAYSENDER);

    command.addOption(CliStrings.START_GATEWAYSENDER__ID, gatewaySenderId);

    if (hasValue(groups)) {
      command.addOption(CliStrings.START_GATEWAYSENDER__GROUP,
          StringUtils.join(groups, StringUtils.COMMA_DELIMITER));
    }

    if (hasValue(members)) {
      command.addOption(CliStrings.START_GATEWAYSENDER__MEMBER,
          StringUtils.join(members, StringUtils.COMMA_DELIMITER));
    }

    return processCommand(command.toString());
  }