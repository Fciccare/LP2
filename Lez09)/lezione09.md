## TreeSet
Treeset< T > offre:
- TreeSet()
- Treeset(Comparator< ? super T >)
```java
public class TreeSet<T extends Comprable<T>> {...}
```
SI può fare anche senza ordinamente naturale ma si ancoregerremo solo a runtime che non godono di ordinamento a runtime

## Contratti TreeSet
Treeset ingnora `equals()` si base solo sul `comprator`
### Esempio
```java
TS<String> s = new TS<>(String.CASE_INSENTIVE...);//Questo non è corente col `equals()` delle String.

s.add("ciao"); //TRUE
s.add("CIAO"); //FALSE, poiché è, case_insentive con la prima add, poiché non ammette duplicati.


```

## SortedSet
- `first(): T` ha complessità costante
- `last(): T` ha complessità costante
- `contains()` complessità logaritmica
- `add()` logaritmica
- `remove()` logaritmica
## HashSet
- `first(): T` ha complessità costante
- `last(): T` ha complessità costante
- `contains()` complessità logaritmica
- `add()` costante
- `remove()` costante
## Osservazioni
Bisogna fare attenzioni che gli oggetti non sia variabili, (esempio potrebbe cambiare hash e quindi potrebbe essere perso), la soluzione migliore sarebbe non usare proprio classi mutabili.
## Esercitazione
```java
class Controller{
	private Set<Function> all;
	public static class Function{
		private Set<Function> incompatible;
		
		public void turnOn(){//senza static
			//Lista: QUADRATICO!!! (n^2)
			//Set: Lineare
			//Treeset: logartmico
			for(Function f: all){
				if(incompatible.contain(f))	
			}
		}
		
		public void turnOn(){//static
			//Ogni struttura è uguale
			for(Function f: incompatible)
				f.turnOff();
		}
	}
}
```
## Program to an Interface
È consigliare usare l'attributo col tipo più astratto possibile (interfaccia) e poi instanzarlo con una classe concreta.
```java
List<String> lista = new ArrayList<>();
Set<Function> set = new HashSet<>();
```
## Ridondanze Booleane
```java
if(exp==true)//Brutto
if(exp)//Bello

if(exp){//Funziona uguale
	return true;
}else{
	return false;
}
```
## Array Associativi
Sono array ma gli indici sono arbitrari.
È un insieme di coppie chiave-valore, la chiave è univoca(con tipo arbitrari)(i dizionari di python sono aa).
In Java esistono due implentazione:
- HashMap
- TreeMap
Metodi principali di Map:
- `put(K,V):V` Da come ritorno il vecchio valore
- `get(Object):V` Da il valore associato a quella chiave, altrimenti `null`.
- `remove(Object):V` Se c'è una coppia con questa chiave la rimuove e da il valore.
- `size(): int` Numero di Coppie
Per iterare possiamo usare:
- `KeySet(): Set<K>` Insieme delle chiavi, le chiave non sono duplicati ecco perché Set
- `values(): Collection<V>` Insieme dei valori, i valori possono essere duplicati ecco perché Collection.

Un concreata iterazione sarebbe:
```java
for(K k: map.KeySet()){
	System.out.println(map.get(k));
}
```

## SortedMap
Rispetto a `Map<K,V>` è presente l'ordinamento delle chiavi.
Aggiunge a `Map<K,V>` le seguenti funzioni:
- `firstKey(): K`
- `lastKey(): K`

## HashMap<K,V>
È simile ad `HashSet`, abbiamo però bisogno di un ordinamento sulle chiavi.
È analoga a `Set`, poiché `HashSet` usa `HashMap` ma senza valori.
## Esercizio
Scrivere un metodo che accetta una mappa `boolean isInjective()` e controlla se è iniettiva, quindi che due chiave diverse non vadano allo stesso valore.
```java
boolean isInjective(Map<K,V> map){
	//Prima proposta, ma sempre true
	//È inutile
	return map.KeySet().size == map.values().size();

	//Seconda proposta
	//Tempo Lineare
	Set<V> temp = new HashSet<>();
	for(K key: map.keySet()){
		//In base all'instanza get può cambiare complessità.
		if(!temp.add(map.get(key))){
			return false;
		}
	}
	return true;

	//Terza proposta
	Set<V> temp = new HashSet<>();
	for(V val: map.values()){
		//QUesto è sempre constante
		if(!temp.add(val){
			return false;
		}
	}
	return true;
	
}
```
Nella terza proposta non usiamo più che le chiavi quindi possiamo sostituire con:
```java
boolean isInjection(Map<?,?> map){
	Set<Object> temp = new HashSet<>();
		for(Object val: map.values()){
			if(!temp.add(val){
				return false;
			}
		}
		return true;
}
```
Preferiamo il jolly perché con la vesione parametrica dobbiamo specifare il tipo, oppure affidarci alla `typeInference`.

## Esercizio Comparator
```java
class Point{//Asse Cartesiano
	int x,y;
	
}

compare(Point a, Point b){
	1) return a.x - b.x;//È corretto
	2) return a.x - b.y;//Non è corretto
}
```
