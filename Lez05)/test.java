import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.print.DocFlavor.STRING;

class test {
    

    public static void main(String[] argv){
        ArrayList<String> l = new ArrayList<>();
        l.add("Francesco");
        l.add("francesco ma in minuscolo");
        //printAll(l); //è uno schifo

        //Meglio usare il for potenziato che sotto usa l'iteratore
        for(String i: l){
            System.out.println(i);
        }

        //Formula Equivalente
        //Non può essere Object
        Iterator<?> i = l.iterator();//Resituisce Iterator<?>
        while(i.hasNext()){
            System.out.println(i.next());
        }
        //Non si può assegnare un Jolly a Object
        //Ma il contrario invece è possibile

        /* ******* HashSet */
        HashSet<Employee> set = new HashSet<>();
        set.add(new Employee("Pippo", 2000, "Via Roma")); //da true
        set.add(new Employee("Pippo", 2000, "Via Napoli")); //da true
        //Facendo così abbiamo violato il contratto secondo il nostro equals.
        //Entra nel bucket e poi fai gli equals
        //Ma non possiamo neanche affermare true poiché può darsi che l'hash sia uguale
    }

    void printAll(List<?> l){//Quadratica (uno schifo)
        for (int i=0; i<l.size(); i++){
            Object x = l.get(i); //è lettura quindi posso assegnare
            System.out.println(x);
        }
    }

    <T> void printAll2(List<T> l){//poco senso
        Iterator<?> i = l.iterator();
        //foo
    }

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

    public boolean equals(Object o){
        if (!(o instanceof Employee)) return false;
        Employee e = (Employee)o;
        return e.name.equals(name) && e.salary==(salary);
    }

    //Funziona sempre, anche con equals
    //ma concettualmente sbagliata!
    //È come se avessimo una lista
    public int hashCode1() {
        return 0;
    }

    //È coerente con equals
    //Funziona meglio della prima impl.
    //Ma non stiamo sfruttando tutta l'info.
    public int hashCode2() {
        //Usciamo l'hashCode di String
        return name.hashCode();
    }

    public int hashCode() {
        //Usiamo l'hashCode di String
        //Ma possiamo fare l'xor con salary
        //Questo è un ottimmo hashing
        //È QUELLO CHE NORMALMENTE I VARI IDE
        return name.hashCode()^salary;
        //^ XOR BIT A BIT
        //È una variabile aeleatoria
    }

    //Non è più coerente 
    //Perché nell'equals manca address
    //Due Employees uguali ma con address diverso
    public int HashCode4(){
        return name.hashCode()^salary^address.hashCode();
    }
}