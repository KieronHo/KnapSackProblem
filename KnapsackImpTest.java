import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class KnapsackImpTest {

	@Test
	void testFractionalKnapsack() {
		KnapsackImp backPack = new KnapsackImp();
		int[] weights = {10, 5, 20, 15, 30};
		int[] values = {5, 20, 15, 30, 1};
		assertEquals(58, backPack.fractionalKnapsack(weights, values, 30 ));
		
		KnapsackImp secondBackPack = new KnapsackImp();
		int[] weights2 = {1, 2, 3};
		int[] values2 = {2, 3, 4};
		assertEquals(8, secondBackPack.fractionalKnapsack(weights2,  values2, 5));
		
		int[] weights3 = {15, 12, 38, 5, 22, 21, 35, 11, 40, 39};
		int[] values3 = {49, 46, 3, 45, 3, 37, 14, 38, 34, 19};
		assertEquals(178, backPack.fractionalKnapsack(weights3,  values3,  43));
	}
	
	@Test
	void testDiscreteKnapsack() {
		KnapsackImp discretePack = new KnapsackImp();
		int[] weights = {1, 2, 3};
		int[] values = {2, 3, 4};
		assertEquals(7, discretePack.discreteKnapsack(weights, values, 5));
		
		int[] weights2 = {10, 20, 30};
		int[] values2 = {60, 100, 120};
		assertEquals(220, discretePack.discreteKnapsack(weights2,  values2,  50));
		
	}
	
	
	
}
