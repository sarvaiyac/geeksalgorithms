package tempPrj;



class Person{
	int age;
	String name;
	Person parent;
	
	Person(int x, String n, Person p){
		this.age = x;
		this.name = n;
		this.parent = p;
	}
}

public class LinkedListImplementation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Person pardada = new Person(190, "danadada", null);
		
		
		Person dadaji = new Person(90, "heerjibhai", pardada);
		
		
		Person parent1 = new Person(46, "mansukhbhai", dadaji);
		
		Person parent2 = new Person(40, "geetaben", null);
		
		Person c1 = new Person(26, "chintan", parent1);
		Person c2 = new Person(26, "hetal", null);
		
		int length = solution(c1);
		System.out.println(length);
		
	}

	private static int solution(Person x) { // [1,0,1,1,1,1,10,1];
		// TODO Auto-generated method stub
		return recursiveSolution(x) + 1;
	}
	
	private static int recursiveSolution(Person p) {
		if (p.parent == null) return 0;
		else return recursiveSolution(p.parent) + 1;
	}

}
