package ru.geekbrains.java_for_testers.generics;

public class BoxComp implements Comparable<BoxComp> {

private int size;

public BoxComp(int size) {
        this.size = size;
        }

@Override
public int compareTo(BoxComp o) {
        if (o == null) {
        throw new RuntimeException("Object is null!");
        }
        return this.size - o.size;
        }

        //    @Override
//    public int compareTo(Object o) {
//        if (!(o instanceof BoxComp)) {
//            throw new RuntimeException("Invalid box type:" + o.getClass().getSimpleName());
//        }
//        BoxComp another = (BoxComp) o;
//        return this.size - another.size;
//    }
}
