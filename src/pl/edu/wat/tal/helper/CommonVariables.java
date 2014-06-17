package pl.edu.wat.tal.helper;

import org.junit.ClassRule;

/**
 * Klasa pomocnicza do trzymania sta³ych programowych
 * 
 */
public class CommonVariables
{
    public static final String ALGORITHM_BRUTE_FORCE = "ALGORYTM BRUTE FORCE";
    public static final String ALGORITHM_LAYERING = "ALGORYTM WARSTWOWY";

    public int SERIA_POMIAROW_OD = 3;
    public int SERIA_POMIAROW_DO = 3;
    public int LICZBA_GENEROWANYCH_GRAFOW_W_SERII = 1;
    public int LICZBA_SERII_POMIAROW_DLA_JEDNEGO_ZADANIA = 1;
    public double GESTOSC_GRAFU = 0.7;
    // public static final int LICZBA_WIERZCHOLKOW_W_GRAFIE = 15;
    public int LICZBA_SPOJNYCH_SKLADOWYCH_W_GRAFIE = 1;

    public boolean CZY_WAGOWY = true;
    public boolean WAGI_ROWNE_JEDEN = true;
    public boolean WAGI_LOSOWE_BEZ_POWTORZEN = false;
    public boolean WAGI_LOSOWE_Z_POWTORZENIAMI = false;
    public boolean GENERUJ_ALGORYTM_BRUTE_FORCE = true;
    public boolean GENERUJ_ALGORYTM_WARSTWOWY = true;
    public boolean GENERUJ_POMIAR_ZLOZONOSCI_OBLICZENIOWEJ = true;
    public boolean GENERUJ_POMIAR_ZLOZONOSCI_PAMIECIOWEJ = false;

    private CommonVariables()
    {
    }

    private static class CommonVariablesHolder
    {
        private static final CommonVariables INSTANCE = new CommonVariables();
    }

    public static CommonVariables getInstance()
    {
        return CommonVariablesHolder.INSTANCE;
    }
}
