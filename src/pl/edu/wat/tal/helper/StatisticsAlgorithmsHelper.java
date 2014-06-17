package pl.edu.wat.tal.helper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Pozwala na prezentowanie z�o�ono�ci obliczeniowej
 * oraz pmai�ciowej algrorytmu.
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
        System.out.println( "Z�O�ONO�� OBLICZENIOWA " + algorithmName + " = " + totalDiffForComputeComplexity + " ns" );
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
        
        results.append( "			�redni czas Brute-Force: " + bruteForceAvarage + "\n");
        results.append( "			�redni czas Layering: " + layerAvarage + "\n" );

        if(bruteForceAvarage > layerAvarage)
        {
            ratio = (double) bruteForceAvarage/layerAvarage;
            ratio = (double) Math.round(ratio * 1000) / 1000;
            results.append( "			Layering by� " + ratio + " razy szybszy od Brute-Force\n" );
        }else
        {
            ratio = (double) layerAvarage/bruteForceAvarage;
            ratio = (double) Math.round(ratio * 1000) / 1000;
            results.append( "			Brute-Force by� " + ratio + " razy szybszy od Layering\n" );
        }

        return results.toString();
    }

}
