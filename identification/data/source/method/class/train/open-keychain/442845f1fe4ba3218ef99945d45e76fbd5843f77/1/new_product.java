public void resetAndWipeToken() throws IOException {
        exhausePw1Tries();
        exhaustPw3Tries();

        // secure messaging must be disabled before reactivation
        connection.clearSecureMessaging();

        // NOTE: keep the order here! First execute _both_ reactivate commands. Before checking _both_ responses
        // If a token is in a bad state and reactivate1 fails, it could still be reactivated with reactivate2
        CommandApdu reactivate1 = connection.getCommandFactory().createReactivate1Command();
        connection.communicate(reactivate1);

        CommandApdu reactivate2 = connection.getCommandFactory().createReactivate2Command();
        ResponseApdu response2 = connection.communicate(reactivate2);
        if (!response2.isSuccess()) {
            throw new CardException("Reactivating failed!", response2.getSw());
        }

        connection.refreshConnectionCapabilities();
    }