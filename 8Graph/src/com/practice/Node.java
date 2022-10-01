package com.practice;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int val;
    List<Node> neighbors;

    Node (){
        val = 0;
        neighbors = new ArrayList<>();
    }

    Node (int val){
        this.val = val;
        neighbors = new ArrayList<>();
    }

    Node (int val, ArrayList<Node> neighbors){
        this.val = val;
        this.neighbors = neighbors;
    }
}
