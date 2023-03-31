## Lista concatenata con Iteratore


```Java
public class Lista<T>{//Lista concatenata
	//definiamo delle API
	public static void main(String[] argv){
		Lista<String>  l = new Lista<>;
		l.add("uno");
		l.add("due");
		
		// API PER ITERARE
		
		//V.1 accesso posizionale (non è standard) perché potrebbe essere quadratica, come in questo caso, poiché get() inizia sempre dall'inizio
		for(int i=0; i<l.size(); ++i){
			System.out.println(l.get(i));
		}
		
		//V.2 (intermedia) ne posizionale ne iteratore
		//Simile a un iteratore, ma li stiamo invocando direttamente sulla lista
		//È Lineare ma non si può iterare più di una volta 
		while (l.hasNext()){
			System.out.println(l.next();
		}//next(), hasNext() standard
		
		//V2.1
		l.reset();//Questo fix il non poter essere iterato più di una volta
	
		//V.3
		//Andiamo a fixare il problema di non poter fare due cicli annidati (due cursori)
		//Anche il thread è un problema perché il cursor è condiviso
		//Unica soluzione: ITERATORE
		Iterator<String> i = l.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}	
	}
}
```

### Implementazione
```java
public class Lista<T> implemnets Interable<>{
	Nodo<T> testa;
	Nodo<T> coda;
	
	private static class Nodo<U>{//private per nasconderla, con static evitiamo che far in modo che ogni Nodo abbia un riferimento alla Lista, ma perde la visibilità di Lista e quindi di T, quindi dobbiamo aggiungere un paremetro a Nodo.
		Nodo<U> next;
		U valore;
		//è inutile mettere private essendo già privata la classe
		Nodo(U val, Nodo<U> n){
			valore = val;
			next = n;
		}
	}
	
	public void add(T elem){
		Nodo<T> nodo = new Nodo<>(elem, null);
		if(coda == null){
			testa = coda = nodo;
		}else {
			coda = coda.next = nodo;
		}
			
	}
	@Ovveride
	public Iterator<T> iterator(){
		return new Iterator<>() {//Anonima
			Nodo<T> corrente = testa;//Lista.this.testa
			
			@Ovveride
			public boolean hasNext(){
				return corrente != null;
			}
			@Ovveride
			public T next(){
				if(corrente == null){
					throw new NoSuchElementException();
				} else {
					T result = corrente.value;
					corrente = corrente.next;
					return result;
				}
			}
			
			@Ovveride
			public void remove(){
				throw new UnsopportedOperationException();
			}
		};
	}

}
```
## Confronto tra oggetti: Comparable
Abbiamo due interfaccie:
- Comparable
Serve ad far assumere un ordine da noi definito.
Contratto:
- PreCondizione: x è confron. this
- PostCondizione: slide
- Penale: ClassCastException
Comprable è indicato quando una classe da ordinare possiede un unico criterio di ordinamento naturale.

Compator si usando quadno classe da non ha un unico criterio di ordinamento naturale, oppure non si può non si vuole modificare.

Pagina esempio: si potrebbe usare una lambda-expression.

```java
Comparator<List<?>> confrontaLunghezza = new Comparator<>(){
	@Ovveride
	public int compare(List<?>a, List<?> b){
	return a.size() - b.size();
	//return Integer.compare(a.size(), b.size());
	
	}
};
confrontaLunghezza.compare(l1,l2);
```
