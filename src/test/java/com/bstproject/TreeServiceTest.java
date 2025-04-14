package com.bstproject;

import com.bstproject.model.TreeNode;
import com.bstproject.model.TreeData;
import com.bstproject.repository.TreeDataRepository;
import com.bstproject.service.TreeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TreeServiceTest {

    @Mock
    private TreeDataRepository treeDataRepository;

    @InjectMocks
    private TreeService treeService;

    public TreeServiceTest() {
        MockitoAnnotations.openMocks(this); // Init mocks
    }

    @Test
    public void testBuildTree() {
        List<Integer> input = List.of(5, 3, 7);
        TreeNode root = treeService.buildTree(input);

        assertNotNull(root);
        assertEquals(5, root.getValue());
        assertEquals(3, root.getLeft().getValue());
        assertEquals(7, root.getRight().getValue());
    }

    @Test
    public void testSerializeTree() {
        TreeNode root = new TreeNode(5);
        root.setLeft(new TreeNode(3));
        root.setRight(new TreeNode(7));

        String expected = "{ \"value\": 5, \"left\": { \"value\": 3, \"left\": null, \"right\": null }, \"right\": { \"value\": 7, \"left\": null, \"right\": null } }";
        String actual = treeService.serializeTree(root);

        assertEquals(expected.replaceAll("\\s+", ""), actual.replaceAll("\\s+", ""));
    }

    @Test
    public void testSaveTree() {
        List<Integer> input = List.of(1, 2);
        TreeNode root = treeService.buildTree(input);

        TreeData mockData = new TreeData();
        mockData.setInputNumbers(input);
        mockData.setSerializedTree(treeService.serializeTree(root));

        when(treeDataRepository.save(any(TreeData.class))).thenReturn(mockData);

        TreeData saved = treeService.saveTree(input, root);

        assertEquals(List.of(1, 2), saved.getInputNumbers());
        assertTrue(saved.getSerializedTree().contains("\"value\": 1"));
    }
}
