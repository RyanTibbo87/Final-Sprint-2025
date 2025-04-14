package com.bstproject.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class TreeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<Integer> inputNumbers;

    @Lob
    private String serializedTree;

    // Constructors
    public TreeData() {
    }

    public TreeData(List<Integer> inputNumbers, String serializedTree) {
        this.inputNumbers = inputNumbers;
        this.serializedTree = serializedTree;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Integer> getInputNumbers() {
        return inputNumbers;
    }

    public void setInputNumbers(List<Integer> inputNumbers) {
        this.inputNumbers = inputNumbers;
    }

    public String getSerializedTree() {
        return serializedTree;
    }

    public void setSerializedTree(String serializedTree) {
        this.serializedTree = serializedTree;
    }
}
