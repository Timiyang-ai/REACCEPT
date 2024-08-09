private SparseMatrix aat(SparseMatrix AT) {
        int m = nrows;
        int[] done = new int[m];
        for (int i = 0; i < m; i++) {
            done[i] = -1;
        }

        // First pass determines the number of nonzeros.
        int nvals = 0;
        // Outer loop over columns of A' in AA'
        for (int j = 0; j < m; j++) {
            for (int i = AT.colIndex[j]; i < AT.colIndex[j + 1]; i++) {
                int k = AT.rowIndex[i];
                for (int l = colIndex[k]; l < colIndex[k + 1]; l++) {
                    int h = rowIndex[l];
                    // Test if contribution already included.
                    if (done[h] != j) {
                        done[h] = j;
                        nvals++;
                    }
                }
            }
        }

        SparseMatrix aat = new SparseMatrix(m, m, nvals);

        nvals = 0;
        for (int i = 0; i < m; i++) {
            done[i] = -1;
        }

        // Second pass determines columns of aat. Code is identical to first
        // pass except colIndex and rowIndex get assigned at appropriate places.
        for (int j = 0; j < m; j++) {
            aat.colIndex[j] = nvals;
            for (int i = AT.colIndex[j]; i < AT.colIndex[j + 1]; i++) {
                int k = AT.rowIndex[i];
                for (int l = colIndex[k]; l < colIndex[k + 1]; l++) {
                    int h = rowIndex[l];
                    if (done[h] != j) {
                        done[h] = j;
                        aat.rowIndex[nvals] = h;
                        nvals++;
                    }
                }
            }
        }

        // Set last value.
        aat.colIndex[m] = nvals;

        // Sort columns.
        for (int j = 0; j < m; j++) {
            if (aat.colIndex[j + 1] - aat.colIndex[j] > 1) {
                Arrays.sort(aat.rowIndex, aat.colIndex[j], aat.colIndex[j + 1]);
            }
        }

        double[] temp = new double[m];
        for (int i = 0; i < m; i++) {
            for (int j = AT.colIndex[i]; j < AT.colIndex[i + 1]; j++) {
                int k = AT.rowIndex[j];
                for (int l = colIndex[k]; l < colIndex[k + 1]; l++) {
                    int h = rowIndex[l];
                    temp[h] += AT.x[j] * x[l];
                }
            }

            for (int j = aat.colIndex[i]; j < aat.colIndex[i + 1]; j++) {
                int k = aat.rowIndex[j];
                aat.x[j] = temp[k];
                temp[k] = 0.0;
            }
        }

        return aat;
    }