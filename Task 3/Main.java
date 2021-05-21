import java.util.HashMap;
import java.util.Map;

class Main {

    public static void main(String[] args) {
        int[] nums = {1,2,4,4,3,3,0,9,2,3};
        int k = 0;

        System.out.println("Number of Pairs with Given Difference Value: "+findPairsNum(nums,k));
    }

    public static int findPairsNum(int[] nums, int k){
        Map<Integer,Integer> map = new HashMap<>();

        for(int num: nums){
            map.put(num, map.getOrDefault(num,0)+1);
        }

        int count = 0;

        for(int key: map.keySet()){
            if(k != 0){
                int diff = key + k;
                if(map.containsKey(diff)) count++;
            }else{
                if(map.get(key) >= 2) count++;
            }
        }
        return count;
    }

}