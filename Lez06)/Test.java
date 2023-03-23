import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class Test {
    /*                   DISCORSO SU JOLLY INFERIORE E SUPERIORE            */
    //Copiare da in ad out alla soglia di minSalary
    //Non possiamo accetere sottotipi di Employee, esempio Manager
    void copyHigherSalary1(Set<Employee> in, Set<Employee> out, int minSalary){}

    //Cosi accetta tutti i sottotipi di Employee
    //JOLLY LIMITE SUPERIORE
    void copyHigherSalary2(Set<? extends Employee> in, Set<Employee> out, int minSalary){}

    //Stiamo rendedo ancora più flessibile
    //JOLLY LIMITE INFERIORE
    void copyHigherSalary3(Set<? extends Employee> in, Set<? super Employee> out, int minSalary){
        for(Employee e: in){
            if(e.getSalary() >= minSalary){
                out.add(e);
            }
        }

        //Unico modo per scorrere out, ma non ha molto senso
        //Poiché i jolly si usando principalmente per leggere e non per scrivere
        for(Object x: out){
            //foo
        }
    }
    /*                FINE DISCORSO SU JOLLY INFERIORE E SUPERIORE            */

    //Pre Erasure:
        //public static <T extends Employee> void f(T a, T b){}
    //Post Erasure:
        //public static void f(Employee a, Employee b){}

    /*   Esempio Cast strani     */
    public void wrongCast(){
        List<Integer> li = new LinkedList<Integer>();
        List<String> ls = (List<String>) (List<?>) li;//funziona perché -> vedi slide

        li.add(5);
        ls.add("Ciao");

        for(Integer i: li){}//Esplode
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    class Employee{
        String name;
        int salary;
        String address;
    
        public Employee(String name, int salary, String address) {
            this.name = name;
            this.salary = salary;
            this.address = address;
        }

        public int getSalary() {
            return salary;
        }
    }
}
