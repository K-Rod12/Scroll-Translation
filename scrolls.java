import java.util.*;

public class scrolls {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n, a, b;
		
		n = sc.nextInt();
		
		//3 arraylists for each type of scroll
		ArrayList<Scroll> scrolls1 = new ArrayList<Scroll>();
		ArrayList<Scroll> scrolls2 = new ArrayList<Scroll>();
		ArrayList<Scroll> scrolls3 = new ArrayList<Scroll>();
		
		//Traverses through the given integers and initializes the arraylists
		for(int i = 0; i < n; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			
			if(a < b) //type 1 scroll
				scrolls1.add(new Scroll(a,b, 1));
			else if(a > b) //type 2 scroll
				scrolls2.add(new Scroll(a,b, 2));
			else //type 3 scroll
				scrolls3.add(new Scroll(a,b, 3));
			
		}
			
		//Calls for the compareTo method to sort type 1 and type 2 scrolls
		Collections.sort(scrolls1);
		Collections.sort(scrolls2);
		
		//Combines all arraylist into one arraylist
		scrolls1.addAll(scrolls3);
		scrolls1.addAll(scrolls2);
		
		long total;
		
		//initializes the 'total' long variable with the total translation time
		total = sweep(scrolls1);
		
		//print(scrolls1);
		
		//prints out the total translation time
		System.out.println(total);
		
	}
	
	//Calculates the total time to translate all scrolls
	public static long sweep(ArrayList<Scroll> scrolls) {
		
		long total = 0;
		long extra = 0;
		
		//initializes a buffer scroll to act as the previous scroll
		Scroll buffer = new Scroll(0,0,0);
		
		//Traverses through the scroll arraylist
		for(Scroll scroll : scrolls) {
			
			//if the second translator takes more time than the first then add the difference of time to extra
			if((buffer.time2 + extra) > scroll.time1) {
				extra += buffer.time2 - scroll.time1;
			}
			//subtracts the difference in time for every other case just in case the second translator catches up to the first
			else
				extra -= scroll.time1 - buffer.time2;
			
			//if the second translator catches up then keep initializing extra to 0
			if(extra < 0)
				extra = 0;
			
			total += scroll.time1;
			
			//change buffer to the last visited scroll
			buffer = scroll;
		}
		
		//adds the last translation time for the second translator
		total = total + buffer.time2 + extra;
		
		//returns the total translation time
		return total;
	}
	
	public static class Scroll implements Comparable<Scroll>{
		
		int time1, time2, type;
		
		
		//Scroll constructor
		Scroll(int a, int b, int type){
			this.time1 = a;
			this.time2 = b;
			this.type = type;
		}
		
		public int compareTo(Scroll o) {
			if(o.type == 1)//if scroll is of type 1 then order them from least to greatest in terms of the first translation time
				return Integer.compare(time1, o.time1);
			if(o.type == 2)//if scroll is of type 2 then order them from greatest to least in terms of the second translation time
				return Integer.compare(o.time2, time2);
			return Integer.compare(time1, o.time1);//this would be in the case of a type 3 scroll where it would not matter
												   // how it is ordered
		}
		
		//mainly for debugging
		public String toString() {
			return "time1: " + time1 + " time2: " + time2 + " type: " + type;
		}
		
	}
	
	//debugging helper function
	public static void print(ArrayList<Scroll> scrolls) {
		
		for(Scroll hi: scrolls)
			System.out.println(hi);
		
	}

}
