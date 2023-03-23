# Lezione 06 - 23-03-2023

## Jolly limite inferiore 
    Object
    Person
    Employe
    Manager

? super Employee  : va ad Employee in giù
Object permette di iterare da Employee in su

## Implementare i Generics 

C++ uno dei primi linguaggi ad implementare i Generics (template):
```C++
template <typename T1, typename T2>
struct pair{
    T1 first;
    T2 second;

    pair(const T1& a, const T2& b) : first(a), second(b) {} //un costrutto figo!
}
```
Questa implementazione è fatto molto bene, rispetto a Java.
Sostiusce a T1 ecc... la parola(tipo) che vogliamo usare.

Reificazione a tempo di compilazione
Reificazione = rendere concrata qualcosa.

Rispetto a Java, i parametri possono non essere oggetti, quindi esempio: String, int.

## Erasure (Come funzionano i Generics in Java)
Java non usa la Reificazione ma la cancellazione (è l'opposto).
Se il type checking va a buon fine, scarta tutte le informazioni.

I paramentri di tipo vengono rimossi o da Object oppure dal primo limite superiore del parametro.

La coerenza tra tipi, viene fatta tramite Cast (che questa volta non possono fallire)

Rimane qualcosa esempio: scoprire che una classe era parametrica.

## Limitazioni dei Generics
Sono tutte cose che con la reificazioni si potrebbero fare

- I parametri attuali di tipo non produrre effetti a runtime
    Esempio non si può fare ```new T()```, ma T diventa Object
- Non è possibile utilizzare il Jolly per instanziare oggetti
    Esempio: ```new LinkedList<?>``` non si può fare, alternativa ```new LinkedList<Object>```
- Non è possibile instanziare un array di tipo parametrico
    Esempio: ```T[10]``` non si può fare, sempre perché sarebbe Object
    ma invece ```T[] a``` si può fare è solo un riferimento
- Non è possibile usare i parametri di tipo per distungere due versioni di un metodo
    Esempio: slide
- Niente overloading tra parametrichi tra Generics e sui limiti superiori
- Non è possibile usare utilizzare un parametro di tipo per selezionare una determinata versione di un metodo in overloading.
- I parametri di tipo non vanno usati per effettuare conversioni esplicite (cast)
- Non si può applicare instanceof a un parametro formale di tipo o a una classe paramatrica

Altri esempi sulle slide

LinkedList non dovrebbe mai essere usato, per motivi di cache.
Poiché la cache lavora bene con memoria contigua, CacheMiss

Invece ArrayList ha memoria contigua.


## Design by Contract
applicato ad un metodo di una classe, un contratto specifica quale compito il metodo promette di svolgere e quali sono le pre-condizioni richieste.

La pre-condizione riguarda:
- i valori passati al metodo come parametri attuali
- lo stato dell'oggetto su cui viene invocato il metodo

Riassunto schematico: slide

La penale (quando viola un contratto) in Java è il lancio di una eccezzione unchecked

Quindi esistono pre-condizioni e post-condizioni

*Guardati le slide stronzo!*