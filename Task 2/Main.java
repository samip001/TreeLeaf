

import java.util.Arrays;

class Main{
	public static void main(String[] args) {
		int[] nums = { 2,5,9,1,4,7,20,4,6 };
		System.out.println(findKForMinSum(nums));
	}

	public static int findKForMinSum(int[] nums){
		Arrays.sort(nums);
		int length = nums.length;

		int middleValue = (int) nums[length/2];
		System.out.println("middleValue: "+ middleValue);

		int sum=0;
		for(int i = 0; i < length; i++){
			sum += Math.abs(nums[i] - middleValue);
		}

		System.out.println("Sum is: "+sum);

		return middleValue;
	}
}