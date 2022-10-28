package net.dd.sem1.gui.util;

@FunctionalInterface
public interface ThrowingSupplier<V> {
  V get() throws Exception;
}
