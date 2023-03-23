# Lezione 05 - 20-03-2023

## Collezioni standard (Java collection framework)
È una parte della libreria standard dedicata alle collezzioni.

Vari interfaccie:
    - Iterable<T>: iterator(): Iterator<T>
           ^
           |
    - Collection<T>

Iterator<T> ha i seguenti metodi:
    - hasNext(): boolean
    - next(): T
    - remove(): void [OPZ]#rimuove l'ultimo valore resistuito da next(), ma non tocca il prossimo.
Iterable serve a scorrere coll. tramite un pattern.

Collection<T> ha i seguenti (selezioni) metodi:
    - add(T): boolean (inseriesce un elemento)
    - contains(Object): boolean (object equals nella collezzione)
    - remove(Object): boolean (Contains + elimina.)
    - size(): int 
True se la collez. è stata modificata da questa op.

Si usa Object perché si può avere una Collection<Employee> c gli viene passato un Object x (che potrebbe essere employee), e può chiedergli se esempio: c.contrains(x).

- Object è più flessibile (utile) di usare T
- Avere Object non crea rischi runtime error
- Add non può recuperare a runtime il tipo giusto di Object

[con i Generics non funziona instanceof]
Iffati contains e remove, si scorrono tutta la collezzioni anche se il tipo non è giusto.

D: Perché i Generics non supportano instanceof? 
R: Take you time!

Varie interfaccie che estendono Collection<T>:
    - Set<T> 
        -SortedSet<T>
    - List<T>
    - Queue<T>

### Set 
NON AMMETTE DUPLICATI E ORDINE{a,b,a} = {a,b} = {b,a}

Torniamo al caso di prima in caso di duplicati, add da false appunto perché la strutta non è stata modificata!

Anche equals tra set rispetta la propietà base

### List
NON È LISTA CONCETENATA!!!
Ma come lista di oggetti (banale)

Ha due metodi extra (accesso posizionale):
    - get(int): T 
    - set(int, T): T (int deve essere giusto e restituisce il valore precendemente occupato)
Poiché gli oggetti hanno un posto da 0->lunghezza
List è pur sempre un interfaccia quindi non assicura efficcenza ecc...

### Queue
Si può usare solo dalle sue estermità.
Oppure una solo estermità in quel caso STACK
L'interfaccia non definisce FIFO o LIFO

Ha due metodi extra:
    - remove(): T (ovveride senza argomenti)
Non si può avere molto precisione non sapendo ancora se è LIFO o FIFO

### LinkedList<T> (Concreata)
Lista doppiamente concatenata, ecc...

get and set SONO LINEARI
add è costante
contains ha compl. n (lineare)
remove ha compl. n + costante

Per scorrerlo meglio usare un iteratore.
Altrimenti si potrebbe avere complessità n^2.

### ArrayList<T> (Concreata)
Seguenza di oggetti, ma intrepetata come array che si ridemensiona all'occorrenza!
In C++: Vector di frtm mogavero!
ArrayList ha costruttore vuoto, oppure indicare la capacità iniziale.

[capacità != dimensione]
- Capacità: lunghezza array interno.
- Dimensione: quante oggetti ci sono dentro

[dimensione <= capacità]
(dimensione è molto privata :( )

get and set ha complessività costante.
add è costante di caso pessimo (ammortizzata)
(quando esempio facciamo la add proprio quando bisogna reallocare l'array)

contains ha compl. n (lineare)
remove ha compl. n + shift

ATTENZIONE SET NON ALLUNGA LA LISTA
MA SOLO CON ADD() !!!

### RandomAccess (tag interface)
È un interfaccia implementata da ArrayList ma non da LinkedList, non è parametrizzata, ed è vuota!

Anche detta interfaccia tag È COMPLETAMENTE VUOTA!

Il suo contratto è che offre accesso di get e set in tempo constante.

È più un un senso logico, ma non nell'effettivo del codice!

Utile nel caso di future implementazione di List (esempio una nostra), ma comunque indicando che get e set sono costanti.

## Iteratori e quadradicità varie 
Guarda il file java

## Implementazione di Set
Implementazione in cui non contano ripetizioni e ordine

### SortedSet<T> (Interfaccia)
Si applica un ordine tramite relazioni d'ordine
Ha due metodi extra:
    - first(): T
    - last(): T

### HashSet<T> (concreto)
Implementato da una tabella di Hash, usando un codice numerico (hash code).

Se fatta bene offrono le add... in tempo costante.
Impossibile che due oggetti abbiamo hashing diverso.
Ma deve essere il più ampio possibile, questo vuol dire fatto bene!

Esempio le Stringe non sono possibili, poiché le:
#Stringhe > #int.
Quindi vuol dire che le collessioni sono possibili.



### TreeSet<T>
Una SortedSet implementato come un albero di Ricerca Bilanciato (autobilanciato)

### Hashine
Nella classe Object troviamo:

public int hashCode();

La sua versione di default resistuisce un numero corrispondente all'indirizzo dell'oggetto.
MA NON È DETTO CHE ENTRI IN UN INT(32bit).

Gli indirizzi > int.

### Coerenza HashCode - Equals
Per ogni x,y: x.equals(y) -> x.hashCode() == y.hashCode
MA NON IL CONTRARIO
Se hanno hashCode uguale non vuole dire che sono ugali.
Ma il contrario invece è normale che lo sia.

Esempio:
Guarda la classe Employee in java

## Jolly limite inferiore 
vedi lez. 06

## Eraser (Come funzionano i Generics)
vedi lez. 06
