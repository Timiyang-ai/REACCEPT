public static void setDisplayConfiguration(float gamma, float brightness, float contrast) throws LWJGLException {
		if (!isCreated()) {
			throw new LWJGLException("Display not yet created.");
		}
		if (brightness < -1.0f || brightness > 1.0f)
			throw new IllegalArgumentException("Invalid brightness value");
		if (contrast < 0.0f)
			throw new IllegalArgumentException("Invalid contrast value");
		int rampSize = getGammaRampLength();
		if (rampSize == 0) {
			throw new LWJGLException("Display configuration not supported");
		}
		FloatBuffer gammaRamp = ByteBuffer.allocateDirect(rampSize*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		for (int i = 0; i < rampSize; i++) {
			float intensity = (float)i/(rampSize - 1);
			// apply gamma
			float rampEntry = (float)java.lang.Math.pow(intensity, gamma);
			// apply brightness
			rampEntry += brightness;
			// apply contrast
			rampEntry = (rampEntry - 0.5f)*contrast + 0.5f;
			// Clamp entry to [0, 1]
			if (rampEntry > 1.0f)
				rampEntry = 1.0f;
			else if (rampEntry < 0.0f)
				rampEntry = 0.0f;
			gammaRamp.put(i, rampEntry);
		}
		setGammaRamp(gammaRamp);
		Sys.log("Gamma set, gamma = " + gamma + ", brightness = " + brightness + ", contrast = " + contrast);
	}