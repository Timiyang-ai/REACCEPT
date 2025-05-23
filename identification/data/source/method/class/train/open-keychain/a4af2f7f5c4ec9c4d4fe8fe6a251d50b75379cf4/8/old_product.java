public void connectToDevice(final Context ctx) throws IOException {
        // Connect on transport layer
        mCardCapabilities = new CardCapabilities();

        mTransport.connect();

        // Connect on smartcard layer
        // Command APDU (page 51) for SELECT FILE command (page 29)
        CommandAPDU select = new CommandAPDU(0x00, 0xA4, 0x04, 0x00, Hex.decode("D27600012401"));
        ResponseAPDU response = communicate(select);  // activate connection

        if (response.getSW() != APDU_SW_SUCCESS) {
            throw new CardException("Initialization failed!", response.getSW());
        }

        mOpenPgpCapabilities = new OpenPgpCapabilities(getData(0x00, 0x6E));
        mCardCapabilities = new CardCapabilities(mOpenPgpCapabilities.getHistoricalBytes());

        mPw1ValidatedForSignature = false;
        mPw1ValidatedForDecrypt = false;
        mPw3Validated = false;

        if (mOpenPgpCapabilities.isHasSCP11bSM()) {
            try {
                SCP11bSecureMessaging.establish(this, ctx);
            } catch (SecureMessagingException e) {
                mSecureMessaging = null;
                Log.e(Constants.TAG, "failed to establish secure messaging", e);
            }
        }

    }