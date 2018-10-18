/**
 * 
 * @author Kieron Ho 20500057
 *
 */
public class KnapsackImp implements Knapsack {

	/**
	 * fractionalKnapsack solves the maximum value packing problem assuming items may
	 * be split into fractions to fit
	 * @param weights is the array containing the weights of the items
	 * @param values is the array containing the values of the items
	 * @param capacity is the maximum capacity of the knapsack
	 * @return the maximum value able to fit into the knapsack
	 */
	public int fractionalKnapsack(int[] weights, int[] values, int capacity) {
		int numberOfItems = weights.length;
		int maxCapacity = capacity;
		float maxValue = 0;
		int[] itemWeights = weights;
		int[] itemValues = values;
		int[] orderToAdd = new int[numberOfItems];
		
		
		//Make an array that contains the "value efficiency" of each item, using float accuracy
		float[] valueEfficiency = new float[numberOfItems];
		
		
		//fill in the valueEfficiency array
		for(int i = 0 ; i < numberOfItems ; i++) {
			valueEfficiency[i] = (float)itemValues[i]/(float)itemWeights[i];
		}
		
		
		//fill in the orderToAdd array
		boolean[] added = new boolean[numberOfItems];//default boolean is false so can be used here
		for(int i = 0 ; i < numberOfItems ; i++) {//make sure each item is added
			float highestEfficiencyValue = 0;
			int highestEfficiencyItem = 0;
			for(int j = 0 ; j < numberOfItems ; j++) {//iterate through to find the descending efficiencies
			if(highestEfficiencyValue < valueEfficiency[j] && added[j] != true) {
				highestEfficiencyValue = valueEfficiency[j];
				highestEfficiencyItem = j;
			}
			}
			orderToAdd[i] = highestEfficiencyItem;
			added[highestEfficiencyItem] = true;
		}
		
		
		//fill in the knapsack
		int itemsAdded = 0;
		int currentCapacity = 0;
		for(int i = 0 ; i < numberOfItems ; i++){
		if(currentCapacity + itemWeights[orderToAdd[i]] < maxCapacity) {
			currentCapacity += itemWeights[orderToAdd[i]];
			itemsAdded++;
			maxValue += itemValues[orderToAdd[i]];
		}
		else break;
		}
		
		
		//add the remaining item/fraction of an item
		if(itemsAdded < numberOfItems) {
		maxValue += itemValues[orderToAdd[itemsAdded]] * (float)(maxCapacity - currentCapacity) / itemWeights[orderToAdd[itemsAdded]];
		}
		
		return Math.round(maxValue);
	}

	/**
	 * discreteKnapsack solves the maximum value packing problem assuming items
	 * cannot be split into parts to fit
	 * @param weights is the array containing the weights of the items
	 * @param values is the array containing the values of the items
	 * @param capacity is the maximum capacity of the knapsack
	 * @return the maximum value able to fit into the knapsack
	 */
	public int discreteKnapsack(int[] weights, int[] values, int capacity) {
		int numberOfItems = weights.length;
		int[] itemWeights = weights;
		int[] itemValues = values;
		int maxCapacity = capacity;
		
		//create the empty "recursion table" 
		int[][] recurTable = new int[numberOfItems + 1][maxCapacity + 1];
		
		//ensure, regardless of default, that the required values are set to 0
		for(int i = 0 ; i < maxCapacity + 1 ; i++) {
			recurTable[0][i] = 0;
		}
		for(int i = 1 ; i <= numberOfItems; i++) {
			recurTable[i][0] = 0;
		}
		
		//completes the table with the goal of finding the "bottom right" value
		for(int m = 1 ; m <= numberOfItems ; m++) {//iterate through each row
			for(int w = 1 ; w <= maxCapacity ; w++) {//iterate through each column
				int case1 = 0;
				if(w - itemWeights[m-1] >= 0) {//prevent an out of bounds array reference
				case1 = recurTable[m-1][w - itemWeights[m-1]] + itemValues[m-1];
				}
				
				int case2 = recurTable[m-1][w];
				recurTable[m][w] = Math.max(case1, case2);
			}
		}
		
		return recurTable[numberOfItems][maxCapacity];
	}

}
