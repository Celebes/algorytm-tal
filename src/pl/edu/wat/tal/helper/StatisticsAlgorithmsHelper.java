package pl.edu.wat.tal.helper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Pozwala na prezentowanie z³o¿onoœci obliczeniowej
 * oraz pmaiêciowej algrorytmu.
 */
public class StatisticsAlgorithmsHelper
{
    private long startTime = 0;
    private long stopTime = 0;
    private String algorithmName = "";
    private long totalDiffForComputeComplexity = 0;

    public StatisticsAlgorithmsHelper( String algorithmName )
    {
        super();
        this.algorithmName = algorithmName;
    }

    public void startCalculateComplexity()
    {
        this.startTime = System.nanoTime();
    }

    public void stopCalculateComplexity()
    {
        this.stopTime = System.nanoTime();
        totalDiffForComputeComplexity = stopTime - startTime;
    }

    public Long showResult()
    {
        System.out.println( "Z£O¯ONOŒÆ OBLICZENIOWA " + algorithmName + " = " + totalDiffForComputeComplexity + " ns" );
        return new Long( totalDiffForComputeComplexity );
    }

    public static Long getAvarage( ArrayList<Long> values )
    {
        Long sum = 0L;
        for ( Long i : values )
        {
            sum += i;
        }

        return sum / values.size();
    }

    public static String compareAlgorithms( HashMap<String, ArrayList<Long>> values )
    {
        StringBuilder results = new StringBuilder();
        ArrayList<Long> bruteForce = values.get( CommonVariables.ALGORITHM_BRUTE_FORCE );
        ArrayList<Long> layer = values.get( CommonVariables.ALGORITHM_LAYERING );
        Long bruteForceAvarage = getAvarage( bruteForce );
        Long layerAvarage = getAvarage( layer );
        double ratio;
        
        results.append( "Przeciêtna z³o¿onoœæ obliczeniowa Alg. Brute- force: " + bruteForceAvarage + "\n");
        results.append( "Przeciêtna z³o¿onoœæ obliczeniowa Alg. warstwowego: " + layerAvarage + "\n" );

        if(bruteForceAvarage > layerAvarage)
        {
            ratio = (double) bruteForceAvarage/layerAvarage;
            results.append( "Alg. warstwowy by³ " + ratio + " razy szybszy od alg. Brute-force\n" );
        }else
        {
            ratio = (double) layerAvarage/bruteForceAvarage;
            results.append( "Alg. Brute-force by³ " + ratio + " razy szybszy od alg. warstwowego\n" );
        }

        return results.toString();
    }

}
