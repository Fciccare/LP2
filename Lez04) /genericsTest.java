import java.util.ArrayList;
import java.util.List;

class Test{
    public static void main(String[] argv){
        System.out.println("Hello World!");
        
        /* ********* */
        //Manager[] managers1 = new Manager[10];
        //Employee[] employees1 = managers1;
        //employees1[0] = new Employee("Fra", 50);//Errore a runtime

        /* ********* */
        //ArrayList<Manager> managers2 = new ArrayList<Manager>();
        //ArrayList<Employee> employees2 = managers2; //errore a compilazione
        //employees2.add(new Employee("Fra", 50));

        /* ***** */
        //ArrayList<Employee> employees2 = new ArrayList<Employee>();
        //employees2.add(new Employee("Fra", 50));
        //employees2.add(new Employee("Bro", 100));

        //ArrayList<Manager> managers2 = new ArrayList<Manager>();
        //managers2.add(new Manager("Fra", 50));
        //managers2.add(new Manager("Bro", 100));
        
        //System.out.println(Test.getTotalSalary1(employees2));
        //System.out.println(Test.getTotalSalary1(managers2));//errore

        /* ******** */
        //ArrayList<Employee> employees2 = new ArrayList<Employee>();
        //employees2.add(new Employee("Fra", 50));
        //employees2.add(new Employee("Bro", 100));

        //ArrayList<Manager> managers2 = new ArrayList<Manager>();
        //managers2.add(new Manager("Fra", 50));
        //managers2.add(new Manager("Bro", 100));
        
        //System.out.println(Test.getTotalSalary2(employees2));
        //System.out.println(Test.getTotalSalary2(managers2));//errore
    }

    public static int getTotalSalary1(List<Employee> l){
        int tot = 0;
        for (Employee e: l)
            tot += e.getSalary();
        return tot;
    }

    //Ora prende qualunque sottoclasse di Employee
    public static <T extends Employee> int getTotalSalary2(List<T> l){
        int tot = 0;
        for (Employee e: l)
            tot += e.getSalary();
        return tot;
    }

    //List<?> è supertipo di tutte le List
    //Questo metodo accetta due liste qualsiasi
    public static void bar(List<?> l1, List<?> l2) { }

    //Questo due liste dello stesso tipo
    public static <T> void foo(List<T> l1, List<T> l2) { }
}

class Employee{
    public String name;
    public int salary;

    public Employee (){}

    public Employee (String name, int salary){
        this.name = name;
        this.salary = salary;
    }

    public int getSalary() {return salary;}
}

class Manager extends Employee{
    public Manager(){}

    public Manager (String name, int salary){
        super(name, salary);
    }
}

