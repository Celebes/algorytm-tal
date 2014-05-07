package pl.edu.wat.tal.helper;

/*
 * Pozwala na prezentowanie z�o�ono�ci obliczeniowej
 * oraz pmai�ciowej algrorytmu.
 */
public class StatisticsAlgorithmsHelper {
	private long startTime = 0;
	private long stopTime = 0;
	private String algorithmName = "";
	private long totalDiffForComputeComplexity = 0;

	public StatisticsAlgorithmsHelper(String algorithmName) {
		super();
		this.algorithmName = algorithmName;
	}

	public void startCalculateComplexity() {
		this.startTime = System.nanoTime();
	}

	public void stopCalculateComplexity() {
		this.stopTime = System.nanoTime();
		totalDiffForComputeComplexity = stopTime - startTime;
	}

	public Long showResult() {
		System.out.println("Z�O�ONO�� OBLICZENIOWA " + algorithmName
				+ " = " + totalDiffForComputeComplexity + " ns");
		return new Long(totalDiffForComputeComplexity);
	}

}
