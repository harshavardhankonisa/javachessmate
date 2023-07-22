package org.chessengine.models;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private List<TreeNode> children = null;
    private String value;
    private int score;
    private TreeNode sonChoosen = null;

    public TreeNode(String value,int score)
    {
        this.children = new ArrayList<>();
        this.value = value;
        this.score = score;
    }

    public void addChild(TreeNode child)
    {
        children.add(child);
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public List<TreeNode> getChildren(){
        return children;
    }

    public String getValue(){
        return value;
    }

    public void setSonChoosen(TreeNode sonChoosen) {
        this.sonChoosen = sonChoosen;
    }

    public TreeNode getSonChoosen() {
        return sonChoosen;
    }
}
