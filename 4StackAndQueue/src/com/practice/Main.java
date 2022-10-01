package com.practice;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        Deque<Integer> dq = new LinkedList<>();
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(1);
        dq.addLast(2);
        dq.addFirst(0);
//        dq.remove();
//        dq.removeFirst();
        dq.pop();
        System.out.println(dq.peek());
        System.out.println(dq.peekLast());

//        printQueue(dq);
    }

    private static void printQueue(Deque<Integer> dq){
        for (Integer num: dq) {
            System.out.println(num);
        }
    }
}
