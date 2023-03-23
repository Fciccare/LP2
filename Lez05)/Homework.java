import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.RandomAccess;

import javax.imageio.plugins.tiff.TIFFImageReadParam;

/* Esercizio per casa:
    scrivere un metodo statico che accetta una 
    lista qualsiasi e la "rovescia" 
    (inverte il posto degli elementi), 
    usando solo i metodi visti a lezione. 
    Il metodo deve essere void, 
    quindi deve modificare il suo argomento */
class Homework{

    public static void main(String[] argv){
        System.out.println("Hello World!");

        ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("!");

        System.out.println(list);
        reverseList(list);
        System.out.println(list);
    }

    public static <T> void reverseList(List<T> list){
        int size = list.size(), index = 0;
        Iterator<T> i = list.iterator();
        while(index < size){
            
            T value = i.next();
            //System.out.println(value);
            size--;
            T oldValue = list.set(size, value);
            System.out.println("Add " + value + " to index: " + size + ", old value is: " + oldValue);
            i.remove();
            
            index++;
        }
    }

    //Lineare per AL e LL, senza lista di appoggio su ArrayList
    public static <T> void reverseListFaella(List<T> list){
        //Usare RandomAccess con accesso posizionale e swap
        //Altrimenti lista di appoggio su LinkedList

        //È ILLEGALE MODIFICARE (con funzioni standard) 
        //UNA LISTA MENTRE SI STA ITERANDO!!!
        //ConcurrentModificationException (Google it)

        //Per linkedlist è valido la prima soluzioni
        
        if (list instanceof RandomAccess){//ArrayList
            //insert Valentina
        }else{//Linkedlist
            //insert first solution
        }


    }

}

