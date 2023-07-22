package org.chessengine.models;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private final List<TreeNode> children;
    private final String value;
    private final int score;
    private TreeNode sonChosen = null;

    public TreeNode(String value, int score) {
        this.children = new ArrayList<>();
        this.value = value;
        this.score = score;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public int getScore() {
        return score;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public String getValue() {
        return value;
    }

    public void setSonChosen(TreeNode sonChosen) {
        this.sonChosen = sonChosen;
    }

    public TreeNode getSonChosen() {
        return sonChosen;
    }
}
