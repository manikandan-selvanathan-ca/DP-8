import java.util.*;

public class Triangle {


    //Bottom up approach.
    //Leave the last row as such.
    //Start build row before last and iterate each column - Next rows Minimum between current column and next column.
    //The top element would be the minimum cost.
    //TC: O(MN) where M is number of rows and N is number of column
    //SC: O(1) As we are mutating the list itself.
    public int minimumTotalBottomUp(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty())
            return 0;
        int n = triangle.size();
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int value = triangle.get(i).get(j)
                        + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1));
                triangle.get(i).set(j, value);
            }
        }
        return triangle.get(0).get(0);
    }

    //Top Down approach
    //Leave the first item from the list.
    //From second row. Calculate the previous current column and previous column.
    //But in top down we need to handle the edge cases of first and last index
    //If it is first column. get last rows first column
    //else if it is last column. get last rows last column
    // else just take the minimum between i and i-1
    //TC: O(MN) where M is number of rows and N is number of column
    //SC: O(1) As we are mutating the list itself.
    public int minimumTotalTopDown(List<List<Integer>> triangle) {
        if(triangle == null || triangle.isEmpty()) return 0;
        int n = triangle.size(); 
        
        for(int i=1;i<n;i++) {
            for(int j=0;j<triangle.get(i).size();j++) {
                if(j == 0) {
                   triangle.get(i).set(j, triangle.get(i).get(j)+triangle.get(i-1).get(j));
                } else if(j == triangle.get(i).size()-1) {
                    triangle.get(i).set(j, triangle.get(i).get(j)+triangle.get(i-1).get(j-1));
                } else {
                    triangle.get(i).set(j, triangle.get(i).get(j)+Math.min(triangle.get(i-1).get(j), triangle.get(i-1).get(j-1)));
                }
            }
        }
                                               
        List<Integer> lastRow = triangle.get(n-1);
        int result = Integer.MAX_VALUE;
        for(int i=0;i<lastRow.size();i++) {
            result = Math.min(result, lastRow.get(i));                                    
        }
        return result;
    }

    public static void main(String[] args) {
        Triangle triangle = new Triangle();
        List<List<Integer>> list = new ArrayList();
        list.add(Arrays.asList(2));
        list.add(Arrays.asList(3, 4));
        list.add(Arrays.asList(6, 7, 4));
        list.add(Arrays.asList(4, 1, 8, 3));
        int result = triangle.minimumTotalBottomUp(list);
        System.out.println("The result: " + result);
    }
}