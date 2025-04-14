package com.bstproject.controller;

import com.bstproject.model.TreeData;
import com.bstproject.model.TreeNode;
import com.bstproject.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TreeController {

    @Autowired
    private TreeService treeService;

    // Show the input form
    @GetMapping("/enter-numbers")
    public String showForm() {
        return "enter-numbers";
    }

    // Process submitted numbers
    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam("numbers") String numbers, Model model) {
        List<Integer> inputList = Arrays.stream(numbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        TreeNode root = treeService.buildTree(inputList);
        treeService.saveTree(inputList, root);

        String serializedTree = treeService.serializeTree(root);
        model.addAttribute("tree", serializedTree);
        model.addAttribute("input", inputList);

        return "enter-numbers";
    }

    // Show all previous trees
    @GetMapping("/previous-trees")
    public String viewPreviousTrees(Model model) {
        List<TreeData> allTrees = treeService.getAllTrees();
        model.addAttribute("trees", allTrees);
        return "previous-trees";
    }
}
