@RequestMapping(method = RequestMethod.POST, value = "/gateways/receivers", params = "op=start")
  @ResponseBody
  public String startGatewayReceiver(
      @RequestParam(value = CliStrings.START_GATEWAYRECEIVER__GROUP,
          required = false) final String[] groups,
      @RequestParam(value = CliStrings.START_GATEWAYRECEIVER__MEMBER,
          required = false) final String[] members) {
    CommandStringBuilder command = new CommandStringBuilder(CliStrings.START_GATEWAYRECEIVER);

    if (hasValue(groups)) {
      command.addOption(CliStrings.START_GATEWAYRECEIVER__GROUP,
          StringUtils.join(groups, StringUtils.COMMA_DELIMITER));
    }

    if (hasValue(members)) {
      command.addOption(CliStrings.START_GATEWAYRECEIVER__MEMBER,
          StringUtils.join(members, StringUtils.COMMA_DELIMITER));
    }

    return processCommand(command.toString());
  }