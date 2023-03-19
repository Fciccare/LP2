//Per usare funzioni parametriche la classe non deve essere parametrica
class Test {
    //Brutto perché dobbiamo stare attenti al cast
    public static Object getMedianBad(Object[] a){
        return a[a.length/2];
    }

    public static <T> T getMedian(T[] a){
        return a[a.length/2];
    }

    public static void main(String[] argv){
        Employee[] a = {new Employee("Fra"), new Employee("Bro")};
        Employee e1 = (Employee)Test.getMedianBad(a);
        System.out.println(e1.name);

        //<Employee> può essere omesso e capira tramite Type Inference
        Employee e2 = Test.<Employee>getMedian(a); 
        System.out.println(e2.name);

        A<String> a1 = new <Integer>A<String>("ciao", 1); //String è T, Integer è U
        A<Employee> n1 = new A<Employee>(new Employee("Francesco")); //L'altro costruttore
    }
   
}

class A<T>{
    public<U> A(T t, U u) { } //Il paramerto di tipo U verrà usato solo qui
    public A(T t) {}
}



class Employee{
    public String name;
    public Employee(String name) {this.name=name;}
}
