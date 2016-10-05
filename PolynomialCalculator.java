// Name: Yunxi Liu
// USC loginid: yunxiliu
// CS 455 PA2
// Fall 2016
/**
	This section describes the user interface for PolynomialCalculator.
	It is an interactive program that lets users build and do computations on several Polynomial objects.
*/
import java.util.ArrayList;
import java.util.Scanner;

public class PolynomialCalculator {
	public static void main(String[] args){
		
		//initially create an array of 10 polynomials whose initial value is the zero polynomial.
		ArrayList<Polynomial> list = new ArrayList<>();
		for(int i = 0; i < INITIAL_CAPACITY; i++){
			list.add(new Polynomial());
		}
		
		Scanner in = new Scanner(System.in);
		
		//continue reading the commands
		while(true){
			
			System.out.print("cmd> ");
	        String str = in.nextLine();
	        Scanner inLine = new Scanner(str);
	        String command = inLine.next();
	        
	        //deal with different command
	        switch(command.toLowerCase()){
	        	case "create":{
	        		create(inLine, in, list);
	        		break;
	        	}
				case "print":{
					print(inLine, list);
					break;
				}
				case "add":{
					add(inLine, in, list);
					break;
				}
				case "eval":{
					eval(inLine, in, list);
					break;
				}
				case "quit":{
					in.close();
					inLine.close();
					return;
				}
				case "help":{
					help();
					break;
				}
				default:{
					System.out.println("ERROR: Illegal command.  Type 'help' for command options.");
					break;
				}
			}
		}
	}
	
	//create a polynomial
	public static void create(Scanner inLine, Scanner in, ArrayList<Polynomial> list){
		
		//check if the index of the term is valid(between 0 to 9)
		int index = inLine.nextInt();
        if(!isValidIndex(index)){
			return;
		}
		list.set(index, new Polynomial());
		System.out.println("Enter a space-separated sequence of coeff-power pairs terminated by <nl>");

		int i = 0;
		double coeff = 0.0;
		int expo = 0;
		inLine = new Scanner(in.nextLine());
	
		//read the coefficient and component of terms
		while(inLine.hasNext()){
			if(i % 2 == 0){
				coeff = inLine.nextDouble();
			}
			else {
				int expoTypein = inLine.nextInt();
				expo = Math.abs(expoTypein);
				//check if the exponent is non-negative
				if(expoTypein < expo){
					System.out.println("WARNING: The negative exponent will be turned into the absolute value.");
				}
				list.set(index, list.get(index).add(new Polynomial(new Term(coeff, expo))));
			}	
			i++;
		}
		//check if user misses the last component
		if(i % 2 != 0){
			System.out.println("WARNING: You miss the last exponent. The last term will not be put into the polynomial.");
		}
		inLine.close();
	}
	
	//print the polynomial
	public static void print(Scanner in1, ArrayList<Polynomial> list){
		
		//check if the index of the term is valid(between 0 to 9)
		int index = in1.nextInt();
        if(!isValidIndex(index)){
			return;
		}
		System.out.println(list.get(index).toFormattedString());
		
	}
	
	//add two polynomials together
	public static void add(Scanner in1, Scanner in, ArrayList<Polynomial> list){
		
		//check if the index of terms are valid(between 0 to 9)
		int index = in1.nextInt();
        if(!isValidIndex(index)){
			return;
		}
        
		int add1 = in1.nextInt();
		int add2 = in1.nextInt();
		if(!(isValidIndex(add1) && isValidIndex(add2))){
			return;
		}
		list.set(index, list.get(add1).add(list.get(add2)));
		
	}
	
	//calculate the result of term for a given value
	public static void eval(Scanner in1, Scanner in, ArrayList<Polynomial> list){
		
		int index = in1.nextInt();
        if(!isValidIndex(index)){
			return;
		}
		System.out.print("Enter a floating point value for x: ");
		in1 = new Scanner(in.nextLine());
		System.out.println(list.get(index).eval(in1.nextDouble()));
		in1.close();
		
	}
	
	//print help information
	public static void help(){
		
		System.out.println("Type in 'create' to create a new polynomial in a particular position. eg. 'create 1'. And then follow the instructions to add terms.");
		System.out.println("Type in 'add' to add two polynomials and put it in a particular position. eg. 'add 2 3 1' means poly 2 = poly 3 + poly 1.");
		System.out.println("Type in 'print' to print a polynomial. eg. 'print 1'.");
		System.out.println("Type in 'eval' to return the value of the polynomial at a given value. eg. 'eval 1'. And then type in the given value.");
		System.out.println("Type in 'quit' to quit the program.\n");
		System.out.println("Remember there are " + INITIAL_CAPACITY + " polynomials(from index 0 to " + (INITIAL_CAPACITY - 1) + ") that you can create.");
		System.out.println("Remember the exponent of any polynomial term must be non-negative");
		System.out.println("Remember to add space between command and the index.");
		
	}
	
	//private method to check if the input index is valid(between 0 to 9)
	private static boolean isValidIndex(int index){
		
		if(index > INITIAL_CAPACITY - 1 || index < 0){
			System.out.println("ERROR: illegal index for a poly.  must be between 0 and " + (INITIAL_CAPACITY - 1) + ", inclusive");
			return false;
		}
		return true;
		
	}
	
	final private static int INITIAL_CAPACITY = 10;
}
