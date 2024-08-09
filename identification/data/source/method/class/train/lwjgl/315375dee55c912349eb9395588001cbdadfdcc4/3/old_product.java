public static boolean setDisplayConfiguration(float gamma, float brightness, float contrast) {
		assert brightness >= -1.0f && brightness <= 1.0f;
		assert contrast >= 0.0f;
		int rampSize = getGammaRampLength();
		if (rampSize == 0)
			return false;
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
		if (!setGammaRamp(gammaRamp))
			return false;
		if (Sys.DEBUG) {
			System.out.println("Gamma set, gamma = " + gamma + ", brightness = " + brightness + ", contrast = " + contrast);
		}
		return true;
	}