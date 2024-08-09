@RequestMapping(method = RequestMethod.POST, value = "/gateways/senders/{id}",
      params = "op=resume")
  @ResponseBody
  public String resumeGatewaySender(@PathVariable("id") final String gatewaySenderId,
      @RequestParam(value = CliStrings.RESUME_GATEWAYSENDER__GROUP,
          required = false) final String[] groups,
      @RequestParam(value = CliStrings.RESUME_GATEWAYSENDER__MEMBER,
          required = false) final String[] members) {
    CommandStringBuilder command = new CommandStringBuilder(CliStrings.RESUME_GATEWAYSENDER);

    command.addOption(CliStrings.RESUME_GATEWAYSENDER__ID, decode(gatewaySenderId));

    if (hasValue(groups)) {
      command.addOption(CliStrings.RESUME_GATEWAYSENDER__GROUP,
          StringUtils.join(groups, StringUtils.COMMA_DELIMITER));
    }

    if (hasValue(members)) {
      command.addOption(CliStrings.RESUME_GATEWAYSENDER__MEMBER,
          StringUtils.join(members, StringUtils.COMMA_DELIMITER));
    }

    return processCommand(command.toString());
  }