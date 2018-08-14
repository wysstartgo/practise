package autocomplete;

import java.util.HashSet;
import java.util.Set;

public class TenarySearchTree<V extends Comparable<V>> {

    class Node<V extends Comparable<V>> {

        private Character key;

        private V value;

        private Node<V> middleNode;

        /**
         * 节点在树上的深度
         */
        private int depth = 0;

        public Character getKey() {
            return key;
        }

        public void setKey(Character key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<V> getMiddleNode() {
            return middleNode;
        }

        public void setMiddleNode(Node<V> middleNode) {
            this.middleNode = middleNode;
        }

        public Node<V> getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node<V> leftNode) {
            this.leftNode = leftNode;
        }

        public Node<V> getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node<V> rightNode) {
            this.rightNode = rightNode;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        private Node<V> leftNode;

        private Node<V> rightNode;

    }

    Node<V> rootNode;

    public TenarySearchTree() {

    }

    public void put(String key, V value) {
        rootNode = put(rootNode, key, value, 0);
    }

    public V get(String key) {
        Node<V> node = get(rootNode, key, 0);
        if (node == null)
            return null;
        return node.getValue();
    }

    public Set<String> getSimilar(String key) {
        Node<V> node = get(rootNode, key, 0);
        if (node == null) {
            return null;
        } else {
            HashSet<String> set = new HashSet<>();
            // System.out.println(node.getValue());
            if(node.getValue() != null){
                set.add(key);
            }else {
                DFS(key,node.getMiddleNode(),set);
            }
            return set;
        }
    }

    private void DFS(String prefix, Node node, HashSet set) {
        if (node != null) {
            if (node.getValue() != null) {
                set.add(prefix + node.getKey());
            }
            DFS(prefix, node.getLeftNode(), set);
            System.out.println("------------" + (prefix + node.getKey()));
            DFS(prefix + node.getKey(), node.getMiddleNode(), set);
            DFS(prefix, node.getRightNode(), set);
        }
    }

    private void dfs(String key, Node node, HashSet set) {
        // 没有精确匹配到，则深度遍历
        // 从中间节点往下找
        if (node.getValue() == null) {
            // 没有精确匹配到，则深度遍历
            // 从中间节点往下找
            // 中左右匹配
            Node<V> middleNode = node.getMiddleNode();
            Node<V> rightNode = middleNode.getRightNode();
            Node<V> leftNode = middleNode.getLeftNode();
            if (middleNode != null) {
                dfs(key + node.getKey() + middleNode.getKey(), middleNode, set);
            }
            if (rightNode != null) {
                dfs(key, rightNode, set);
            }
            if (leftNode != null) {
                dfs(key, leftNode, set);
            }
        } else {
            // 进入这里表示精确匹配到了
            System.out.println("精确匹配到了:   " + key + node.getKey());
            set.add(key);
            // set.add(key);
        }
    }

    private void dfsOne(String key, HashSet set, Node<V> oneNode) {
        if (oneNode != null) {
            // if(oneNode.getDepth() != key.length() - 1){
            // System.out.println(oneNode.getKey() + "*************" +
            // oneNode.getDepth());
            // return;
            // }
            if (oneNode.getValue() != null) {
                // 得到的值
                String getTedV = key + oneNode.getKey();
                // 深度和得到的值一样时，放入set中去
                if (oneNode.getDepth() == getTedV.length()) {
                    set.add(getTedV);
                } else {
                    System.out.println(oneNode.getValue() + "------" + key + "-------" + oneNode.getDepth());
                }
            } else {
                System.out.println("0000000000000   " + key);
                dfs(key + oneNode.getKey(), oneNode, set);
            }
        } else {
            System.out.println("##################" + key);
            set.add(key);
        }
    }

    private Node<V> get(Node<V> node, String key, int index) {
        Character ch;
        ch = key.charAt(index);
        if(node == null){
            return null;
        }
        if (Character.getNumericValue(ch) < Character.getNumericValue(node.getKey())) {
            return get(node.getLeftNode(), key, index);
        } else if (Character.getNumericValue(ch) > Character.getNumericValue(node.getKey())) {
            return get(node.getRightNode(), key, index);
        } else if (index < key.length() - 1) {
            return get(node.middleNode, key, index + 1);
        } else {
            return node;
        }

    }

    // public Node<V> put(Node<V> node, String key, V value, int index) {
    // Character ch;
    // ch = key.charAt(index);
    // if (node == null) {
    // node = new Node<V>();
    // node.key = ch;
    // }
    // Queue leftNodeQueue = new LinkedList();
    // Queue rightNodeQueue = new LinkedList();
    // Queue middleNodeQueue = new LinkedList();
    // while (index < key.length()){
    // ch = key.charAt(index);
    // if (Character.getNumericValue(ch) <
    // Character.getNumericValue(node.getKey())) {
    // leftNodeQueue.add(new ADDNode(node.getLeftNode(),ch,value));
    // //node.setLeftNode(put(node.getLeftNode(), key, value, index));
    // } else if (Character.getNumericValue(ch) >
    // Character.getNumericValue(node.getKey())) {
    // rightNodeQueue.add(new ADDNode(node.getRightNode(),ch,value));
    // //node.setRightNode(put(node.getRightNode(), key, value, index));
    // } else if (index < key.length() - 1) {
    // middleNodeQueue.add(new ADDNode(node.getMiddleNode(),ch,value));
    // //node.setMiddleNode(put(node.getMiddleNode(), key, value, index + 1));
    // } else {
    // node.setValue(value);
    // break;
    // }
    // }
    // return node;
    // }
    //
    // class ADDNode{
    // private Node node;
    // private Character ch;
    // private V value;
    //
    // public ADDNode(Node node, Character ch, V value) {
    // this.node = node;
    // this.ch = ch;
    // this.value = value;
    // }
    // }

    /**
     * 广度遍历，需要用递归解决
     * 
     * @param node
     * @param key
     * @param value
     * @param index
     * @return
     */
    public Node<V> put(Node<V> node, String key, V value, int index) {
        Character ch;
        ch = key.charAt(index);
        if (node == null) {
            node = new Node<V>();
            node.key = ch;
        }
        node.setDepth(index + 1);
        // System.out.println(node.getKey() + "----------" + node.getDepth());
        if (Character.getNumericValue(ch) < Character.getNumericValue(node.getKey())) {
            node.setLeftNode(put(node.getLeftNode(), key, value, index));
        } else if (Character.getNumericValue(ch) > Character.getNumericValue(node.getKey())) {
            node.setRightNode(put(node.getRightNode(), key, value, index));
        } else if (index < key.length() - 1) {
            node.setMiddleNode(put(node.getMiddleNode(), key, value, index + 1));
        } else {
            node.setValue(value);
        }
        return node;
    }

    /**
     * 广度遍历，需要用递归解决
     * 
     * @param node
     * @param key
     * @param value
     * @param index
     * @return
     */
    // public Node<V> put(Node<V> node, String key, V value, int index) {
    // Character ch;
    // ch = key.charAt(index);
    // if (node == null) {
    // node = new Node<V>();
    // node.key = ch;
    // }
    // if (Character.getNumericValue(ch) <
    // Character.getNumericValue(node.getKey())) {
    // node.setLeftNode(put(node.getLeftNode(), key, value, index));
    // } else if (Character.getNumericValue(ch) >
    // Character.getNumericValue(node.getKey())) {
    // node.setRightNode(put(node.getRightNode(), key, value, index));
    // } else if (index < key.length() - 1) {
    // node.setMiddleNode(put(node.getMiddleNode(), key, value, index + 1));
    // } else {
    // node.setValue(value);
    // }
    // return node;
    // }

    public static void main(String[] args) {
        TenarySearchTree<Integer> tstInteger = new TenarySearchTree<>();
        // tstInteger.put("rajasekar", 35);
        // tstInteger.put("ramkumar", 34);
        tstInteger.put("SOYBEAN", 36);
        tstInteger.put("TTYXAGUSD", 37);
        tstInteger.put("399001.SZ", 99);
        tstInteger.put("399506.SZ", 39);
        tstInteger.put("398506.SZ", 39);
        tstInteger.put("399300.SZ", 37);
        tstInteger.put("我是中国人",33);
        tstInteger.put("JAKARTASTOCKEXCHANGECOMPOSITEINDEX", 66);
        // System.out.println(tstInteger.get("rajasekar"));
        // Integer sp = tstInteger.get("399001.SZ");
        // System.out.println(sp);
        Set<String> sp1 = tstInteger.getSimilar("39");
        if (sp1 != null) {
            for (String i : sp1) {
                System.out.println("==============" + i);
            }
        }
    }

}