package Generics;

class Pair<S,T> {
    private S first;
	private T second;

	public Pair(S a, T b) {	first = a; second = b; }

	public S getFirst() { return first; }
    public T getSecond() { return second; }

    public static void main(String[] args){
        Pair<String,Integer> p = new Pair<>("Bella", 100);
        System.out.println(p.getFirst());
        System.out.println(p.getSecond());
    }

}
