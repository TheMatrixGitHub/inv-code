package 打印树;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: sl
 * Date: 2021/9/16
 * Time: 10:20
 */
public class PrintTree {

    @Data
    public static class Node {
        /**
         *
         */
        private Integer id;

        /**
         *
         */
        private Integer parentId;

        /**
         *
         */
        private String name;

        /**
         *
         */
        private Integer level;


        /**
         *
         */
        private List<Node> children;

        public Node(Integer id, Integer parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }

        public Node(Integer id, Integer parentId, String name, Integer level) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
            this.level = level;
        }
    }


    @Test
    public void printTree() {

        List<Node> nodeList = new ArrayList<>();
        nodeList.add(new Node(1, null, "aa"));
        nodeList.add(new Node(2, 1, "bb"));
        nodeList.add(new Node(3, 1, "cc"));
        nodeList.add(new Node(4, 2, "dd"));
        nodeList.add(new Node(5, 3, "ee"));

        Node root = buildTree(new Node(1, null, "aa", 0), nodeList);
        printTree(root);

    }

    private Node buildTree(Node parentNode, List<Node> nodes) {
        Integer parentNodeId = parentNode.getId();
        List<Node> childrenNode;
        if (parentNode.getChildren() == null) {
            childrenNode = new ArrayList<>();
            parentNode.setChildren(childrenNode);
        } else {
            childrenNode = parentNode.getChildren();
        }


        for (Node node : nodes) {
            if (Objects.equals(parentNodeId, node.getParentId())) {
                node.setLevel(parentNode.getLevel() + 1);
                childrenNode.add(node);
                buildTree(node, nodes);
            }
        }

        return parentNode;
    }

    private void printTree(Node node) {
        for (int i = 0; i < node.getLevel(); i++) {
            System.out.print(" ");
        }
        System.out.println(node.getName());

        if (node.getChildren() != null) {
            for (Node parentNode : node.getChildren()) {
                printTree(parentNode);
            }
        }
    }
}