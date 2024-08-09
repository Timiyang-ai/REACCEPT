@Override
    public void initialize(final Subject initSubject, final CallbackHandler initCallbackHandler,
            final Map<String, ?> initSharedState, final Map<String, ?> initOptions) {

        this.subject = initSubject;
        this.callbackHandler = initCallbackHandler;

        for (Entry<String, ?> option : initOptions.entrySet()) {
            if (option.getKey().equalsIgnoreCase("debug")) {
                this.debug = Boolean.parseBoolean((String) option.getValue());
            } else if (option.getKey().equalsIgnoreCase("principalFormat")) {
                this.principalFormat = PrincipalFormat
                        .valueOf(((String) option.getValue()).toUpperCase(Locale.ENGLISH));
            } else if (option.getKey().equalsIgnoreCase("roleFormat")) {
                this.roleFormat = PrincipalFormat.valueOf(((String) option.getValue()).toUpperCase(Locale.ENGLISH));
            }
        }
    }