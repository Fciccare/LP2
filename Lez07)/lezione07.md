# Contratti ed Ovverriding
*piccolo recap sul Design by Contract della scorsa volta* (precondizioni, postcondizioni, ecc...)
Se collection stabilisce una precondizione, e postcondizioni su esempio il metodo `add()` come si comporta List, ArrayList?
Attraverso il `principio di Liskov (vedi slide)`
 - Se un client usa una classe A,  deve poter usare allo stesso modo le sottoclassi di A,  senza neppure conoscerle. (in pratica devono essere sostituibili senza il client se ne accorga, quindi senza modifiche)
 - *Forma più precisa:* Le chiamate fatte al metodo originario rispettando il suo contratto  devono continuare ad essere corrette  anche rispetto al contratto ridefinito in una sottoclasse.
 
Esempio List non fa ovveride di `add()` da Collection, ma List può fare ovveride del contratto (linea teorica non effettivo), 

La sottointerfaccia può modificare il contratto senza l'implementazione effettiva, ma devrebbe rispettare il `Principio di sostituibilità`

Le modifiche consentite per rispettare la sostibuilità sono:
- Quindi, ogni ridefinizione del contratto dovrebbe offrire al client almeno gli stessi benefici,  richiedendo al più gli stessi obblighi.
- In altre parole, un overriding può rafforzare la post-condizione (garantire di più) e  indebolire la pre-condizione (richiedere di meno) MA NON VICEVERSA!
- Tale condizione prende il nome di regola contro-variante, perché la pre-condizione può  variare in modo opposto alla post-condizione.

## Esempio in Java
```Java
Vis T foo(U1, ..., Un) throws E1, ..., Em
//Eccezzioni Checked
```
Cosa può cambiare in ovveriding?
- I parametri formali NON devono cambiare
- la visibilità può cambiare in alcuni casi, si può allargare ma non diminuire. (se private non ovveride).
- Nome lo stesso
- Il tipo di ritorno può essere un sottotipo (tipi primitivi non posso cambiare)
- Le eccezzioni possono essere sia aggiunte che tolte, ma deve essere sottotipo di un sottitipo di una di quelle vecchie(una ogni nuova eccezzione deve essere sottotipo di almeno una di quelle vecchie -cit Faella) *si potrebbe anche levare il throws*

```Java
Vis' T' foo(U1, ..., Un) throws F1, ..., Fk
//Sempre Eccezzioni Checked nel throws
```
Questo è un modo per essere più specifico, poiché `T'` è più specifica di `T`, idem per la visibilità anche li si amplia, idem per le eccezzioni.

### Caso particolare
La `sostituibilità` permette di cambiare i passaggi formali indebolendo la precondizioni, quindi possono diventari supertipi dell'originali.
Esempio da `Integer -> Number` questo sarebbe giusto per la `sostituibilità`.
Ma java è più rigido della `sostituibilità` andando a negare la possibilità di poter cambiare i parametri.
Anche per i parametri di ritorno Java era contrario, ma dopo grande richiesta da Java moderno si èuò cambiare il tipo di ritorno con un suo sottotipo.
(però non vale per i tipi primatici, per motivi di layout bit di allocazioni ecc...)

Le regole dell'overrind in Java rispetta solo una metà delle regole delle controvariante.

## Esempio
```Java 
public double avg(String str, char ch)
```
*Pre-condizione*: la stringa str non è null e non è vuota; il carattere `ch` è presente in `str`.
*Post-condizione:* restituisce il numero di occorrenze di `ch` in `str`, diviso la lunghezza di `str`.
*(slide con tutti gli esempi)*

## Javadoc
Serve a documentare il codice, in special modo i contratti, sono dei commenti speciali, con dei tag  per indicare vari parti del contratto `@param, @return @thorws
```Java
/**  
* Calcola la radice quadrata.  
*  
* @param x numero non-negativo di cui si vuole calcolare la radice quadrata (precondizione) 
* @return la radice quadrata di x (postcondizione)
* @throws IllegalArgumentException se x è negativo  (penale)
*/  
public double sqrt(double x){  
	if (x<0) throw new IllegalArgumentException();    
}
```
Estrea in HTML o PDF il tipo di documentazione, che è come viene generata la documentazione delle API di Java.

## Contratti in Eiffel
È fortemente orientato ai contratti, esempio nelle slide.

## Altri casi particolari
Alcune volte è meglio dividere in:
- generale: vincolante per gli ovveride.
- locale: NON vincolante per gli ovveride.
Esempio in `equals()` non è obbligatorio seguire la `sostituibilità`.
Post-Condizione: è uguale a `==` ma non è vincolante per le sottoclassi altrimenti sarebbe inutile.

### Parte Generale `equals()`
- Pre-Condizione: nessuna, tutto lecito
- Post-Condizione: è una relazione di equivalenza tra instanze (più completa nella documentazione (slide))
### Parte Locale `equals()`
Questa è valida solo per `Object` e non vincolante per le sottoclassi, quindi uscendo la concetto di `sostituibilità`.
- Pre-condizione: nessuna
- Post-condizione: l'invocazione `x.equals(y)` è uguale a `x==y` se `x` non sia null.

## Esercitazione in CrowGradle
A fine lezione link per l'esercitazione.
I voti sono sui giudizi personale e in base quanto siete stati bravi a giudicare gli altri.

Fino giovedi: upload
Da venerdì: correzzioni
(NESSUN IMPATTO SULLA VALUTAZIONE, MA FORTEMENTE CONSIGLIATA)

25% valutazioni su gli altri.
75% sulla nostra prova.

Usato anche nelle prove intercorse, su 4 esercizi, uno trascriverlo a casa e bla bla...
---- PAUSA ---
## Classi Locali (senza slide)
Se una classa interna si trova all'interno di uno scope interno di una funziona si chiama LOCALE.
```java
class A{
	void f(){
		class B{//locale
			...
		}
	}
}
```
### Differenza con classi interna
La classe B
- non può avere nessun modificatore
- la sua visibilità è limitato al metodo `f()` (è meno visibile di una classe interna privata).
- Può accedere a tutti gli attribuiti e metodi della classe A
- Gli oggetti di B hanno un riferimento di A solo se è il metodo non è statico.
```java
class A{
	void f(int n){//B non può essere tipo di ritorno, ma es. Object va bene, ma un iteratore su B esempio va bene.
		String msg = ...;
		class B{//locale
			...
		}
	}
	B b = new B();//è abbrevazione di this
}
```
Questo va bene:
```java 
class A{
	Object f(int n){
		String msg = ...;
		class B{//locale
			System.out.println(n);
		}
		B b = new B();
		return b;
	}
}
```

La classe B ha accesso a `n` e `msg`?
Si può fare solo con un vincolo, il codice di B può usare le variabile a patto che le variaibli siano `Effectivily Final` (il compilatore controlla).
Cioè sono 
- final
- o come se lo fossero (cioè che non cambiano)
*potrei metterci `final` senza aver errore di compilazione, quindi è effettivamente final*
B vive di più rispetto agli attribuiti quindi possono esserci dei problemi ecco perché B si fa una copia (nascosta) di tutti gli attributi `Effectevily Final`.
Anche detto che `B cattura n`.
(Quindi si applica solo a variabili sullo stack che vivono poco)

### Discorso sul perché (domanda)
Perché non lasciare che solo su B non possa toccare i valori?
```java
Object g(){
	int m = 2;
	class B {
		@Ovveride
		public String toString(){
			return m + ""; //modo veloce per convertire l'intero in stringa!
		}
	}
...
//NON COMPILA POICHÈ m CAMBIA
Object x = new B(); //qua avviene la copia
int m = 3;
return x;
}
//QUESTA COSA NON VA BENE PERCHÈ ESPONE AI PROGRAMMATORI (NAIVE) CHE B ABBIA FATTO UNA COPIA, MA JAVA VUOLE NASCONDERE QUESTA COSA.
```
(le prime versioni di Java volevano `final`, ma tot. versioni in poi, hanno introdutto `Effectivaly Final`)
questa cosa si può verificare con `javap` che printa il bytecode.

## Classi Anonima
andiamo sempre più a compattare.
Non si può dichiare senza instanziare, ma solo su base di una **interfaccia esistente**.
```java
new I(){//la graffa indica che non sto instanziando l'interfaccia altrimenti errore.
	...
}
//LA VERSIONE DI SOPRA È LA VERSIONE COMPATTA ED EQUIVALENTE A QUESTA SOTTO.
class A implements I{
	...
}
new A();
```

## Classe Anonima da Classe Esistente
```java
new C(a1,...,an){
...
}

//LA VERSIONE DI SOPRA È LA VERSIONE COMPATTA ED EQUIVALENTE A QUESTA SOTTO.
class A extends C{
...
}
new A();

//QUESTO PASSAGGIO VIENE SEMPLICATO DALLA PRIMA SCRITTURA.
class A extends C{
	public A(b1,...,bn){
		super(b1,...,bn)
	}
}
new A();
```
Un possibile esempio è il `ActionListener()` che usiamo per collegare una funzione al click.

## Esempi dalle slide
[Link slide da pagina 12 in poi] (http://wpage.unina.it/m.faella/Didattica/LpII/slides/6-nesting.pdf)

`makeSpecialEmployee` è sia classe Anonima che Locale (statica).
Non tutte le classi Anonime sono Locali!

(l'esercizio pg.17 è uscito a qualche esame)

Esercizio l'ultima slide per casa?

extra ultimi 3 minuti: pagina 9 slide
