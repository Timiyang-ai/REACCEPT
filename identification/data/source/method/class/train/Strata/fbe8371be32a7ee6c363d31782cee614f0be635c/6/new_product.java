public SwaptionSabrSensitivity multipliedBy(double factor) {
    return new SwaptionSabrSensitivity(
        convention,
        expiry,
        tenor,
        currency,
        alphaSensitivity * factor,
        betaSensitivity * factor,
        rhoSensitivity * factor,
        nuSensitivity * factor);
  }