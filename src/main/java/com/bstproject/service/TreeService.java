package com.bstproject.service;

import com.bstproject.model.TreeData;
import com.bstproject.model.TreeNode;
import com.bstproject.repository.TreeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeService {

    @Autowired
    private TreeDataRepository treeDataRepository;

    // Insert numbers into BST
    public TreeNode buildTree(List<Integer> numbers) {
        TreeNode root = null;
        for (int num : numbers) {
            root = insertIntoBST(root, num);
        }
        return root;
    }

    // Insert helper method
    private TreeNode insertIntoBST(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.getValue()) {
            root.setLeft(insertIntoBST(root.getLeft(), value));
        } else {
            root.setRight(insertIntoBST(root.getRight(), value));
        }
        return root;
    }

    // Convert tree to JSON-like string
    public String serializeTree(TreeNode node) {
        if (node == null) return "null";
        return "{ \"value\": " + node.getValue()
                + ", \"left\": " + serializeTree(node.getLeft())
                + ", \"right\": " + serializeTree(node.getRight()) + " }";
    }

    // Save input + tree structure to DB
    public TreeData saveTree(List<Integer> inputNumbers, TreeNode root) {
        String treeStr = serializeTree(root);
        TreeData treeData = new TreeData();
        treeData.setInputNumbers(inputNumbers); 
        treeData.setSerializedTree(treeStr);
        return treeDataRepository.save(treeData);
    }

    // Load all saved trees
    public List<TreeData> getAllTrees() {
        return treeDataRepository.findAll();
    }
}
