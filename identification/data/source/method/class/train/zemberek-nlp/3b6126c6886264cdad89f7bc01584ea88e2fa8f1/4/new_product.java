public float lookup(float logA, float logB) {
            if (logA > logB) {
                final float dif = logA - logB; // logA-logB because during lookup calculation dif is multiplied with -1
                return dif >= 5f ? logA : logA + lookup[(int) (dif * scale)];
            } else {
                final float dif = logB - logA;
                return dif >= 5f ? logB : logB + lookup[(int) (dif * scale)];
            }
        }