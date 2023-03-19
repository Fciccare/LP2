package Generics;

class Pair<T> {
    private T first, second;

    public Pair(T a, T b) {	first = a; second = b; }

    public T getFirst() { return first; }

    public void setFirst(T a) { first = a; }

    public static void main(String[] argv){
        //Nella dichiarazione della variabile è OBBLIGATORIO indicarne il tipo
        Pair<String> p = new Pair<String>("uno", "due"); 
																					       
        //In alcuni contesti è possibile abbreviare
        Pair<String> p1 = new Pair<>("uno", "due"); //Nell'istanziazione è facoltativo (in questo contesto)

        //Compila ma con un warning (per retrocompatibilità)
        Pair p2 = new Pair("uno", "due"); //Versione grezza della classe - NON SI DEVE FARE!

        /* ************************************************************************************** */
        String s = p.getFirst(); //con i generics non è necessario fare casting
        System.out.println(s);
    }

}

