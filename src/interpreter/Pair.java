package interpreter;

public class Pair<K, V> {
  private K key;
  private V value;

  public Pair(K c, V a) {
    key = c;
    value = a;
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }
}
