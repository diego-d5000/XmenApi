package xmenapi.utils;

public class MutantAnalyzer {
    private static MutantAnalyzer instance = new MutantAnalyzer();

    private String[] dna;
    private int mutantDna = 0;
    private int continuousNitrogenousBase = 0;
    private int dnaWidth;
    private int dnaHeight;

    private MutantAnalyzer() {
    }

    public static MutantAnalyzer getInstance(){
        return instance;
    }

    public boolean isMutant(String[] dna) {
        this.mutantDna = 0;
        this.dna = dna;
        dnaWidth = dna[0].length();
        dnaHeight = dna.length;
        return findMutantDnaHorizontal() || findMutantDnaVertical() || findMutantDnaTransverse();
    }

    private void checkNextNitrogenousBase(int i, int j, int k, int l) {
        try {
            if (dna[i].charAt(j) == dna[k].charAt(l))
                continuousNitrogenousBase++;
            else
                continuousNitrogenousBase = 0;
        }catch (Exception e){
            return;
        }
        if (continuousNitrogenousBase == 3) {
            mutantDna++;
            continuousNitrogenousBase = 0;
        }
    }

    private boolean findMutantDnaHorizontal() {
        for (int i = 0; i < dnaHeight; i++) {
            for (int j = 0; j < dnaWidth - 1; j++) {
                checkNextNitrogenousBase(i, j, i, j + 1);
                if (mutantDna > 1)
                    return true;
            }
        }
        return false;
    }

    private boolean findMutantDnaVertical() {
        for (int i = 0; i < dnaWidth; i++) {
            for (int j = 0; j < dnaHeight - 1; j++) {
                checkNextNitrogenousBase(j, i, j + 1, i);
                if (mutantDna > 1)
                    return true;
            }
        }
        return false;
    }

    private boolean findMutantDnaTransverse() {
        for (int i = 0; i < dnaWidth - 3; i++) {
            for (int j = 0, k = i; j < dnaHeight - 1 && k < dnaWidth - 1; j++, k++) {
                checkNextNitrogenousBase(j, k, j + 1, k + 1);
                if (mutantDna > 1)
                    return true;
            }
        }
        for (int i = 1; i < dnaHeight; i++) {
            for (int j = 0, k = i; j < dnaHeight - 1 && k < dnaWidth - 1; j++, k++) {
                checkNextNitrogenousBase(k, j, k + 1, j + 1);
                if (mutantDna > 1)
                    return true;
            }
            for (int j = i, k = 0; j > 0 && k < dnaWidth - 1; j--, k++) {
                checkNextNitrogenousBase(j, k, j - 1, k + 1);
                if (mutantDna > 1)
                    return true;
            }
        }
        for (int i = 0; i < dnaWidth - 3; i++) {
            for (int j = dnaHeight -1, k = i; j > 0 && k < dnaWidth - 1; j--, k++) {
                checkNextNitrogenousBase(j, k, j - 1, k + 1);
                if (mutantDna > 1)
                    return true;
            }
        }
        return false;
    }

}
