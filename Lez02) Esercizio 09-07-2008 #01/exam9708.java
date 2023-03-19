/*
Data la seguente classe.
    public class Z {
        private Z other;
        private int val;
        ...
    }
Si considerino le seguenti specifiche alternative per il metodo equals
● Due oggetti x e y di tipo Z sono uguali se:
    a) x.other e y.other puntano allo stesso oggetto ed x.val è maggiore o uguale di y.val
    b) x.other e y.other puntano allo stesso oggetto ed x.val e y.val sono entrambi pari
    c) x.other e y.other puntano allo stesso oggetto oppure x.val è uguale a y.val
    d) x.other e y.other sono entrambi null oppure nessuno dei due è null ed x.other.val è uguale a y.other.val
● Dire quali specifiche sono valide e perché
● Implementare la specifica (d)
a) non è simmetrica 
b) non è riflessivo se dispari
c) transitività non vale in teoria
d) giusto
*/

class Z{

    private Z other;
    private int val;

    public boolean equals(Object o) {
        if(!(o instanceof Z)) return false;

        Z z = (Z)o;

        //x.other e y.other sono entrambi null
        if(this.other==null && z.other==null) 
            return true;
            
        //nessuno dei due è null ed x.other.val è uguale a y.other.val
        if(this.other!= null && z.other != null)    
            return this.val == z.val;

        return false;        
    }

    public static void main(String[] argv){
        System.out.println("Hello World");
    }
}

