package com.bstproject.controller;

import com.bstproject.model.TreeData;
import com.bstproject.model.TreeNode;
import com.bstproject.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TreeController {

    @Autowired
    private TreeService treeService;

    // Serve the form (handled by HTML file in /static)
    @GetMapping("/")
    public String welcome() {
        return "Welcome! Go to /enter-numbers.html to input your data.";
    }

    // Process numbers and return JSON with result
    @PostMapping("/process-numbers")
    public Map<String, Object> processNumbers(@RequestParam("numbers") String numbers) {
        System.out.println("Received numbers: " + numbers);
        
        List<Integer> inputList = Arrays.stream(numbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        TreeNode root = treeService.buildTree(inputList);
        treeService.saveTree(inputList, root);

        Map<String, Object> response = new HashMap<>();
        response.put("input", inputList);
        response.put("tree", root);

        return response;
    }

    // View all previous trees as raw JSON
    @GetMapping("/previous-trees")
    public List<TreeData> getPreviousTrees() {
        return treeService.getAllTrees();
    }
}