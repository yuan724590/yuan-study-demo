package yuan.study.demo.entity;


public class BalanceTree {
    
    private TreeNode treeNode;

    /**
     * 通过值查询节点
     */
    public TreeNode searchByValue(int value) {
        if (treeNode == null) {
            return null;
        } else {
            return search(treeNode, value);
        }
    }

    private TreeNode search(TreeNode treeNode, int value){
        if (value == treeNode.getValue()) {
            return treeNode;
        } else if (value < treeNode.getValue()) {
            if (treeNode.getLeftNode() == null) {
                return null;
            }
            return search(treeNode.getLeftNode(), value);
        } else {
            if (treeNode.getRightNode() == null) {
                return null;
            }
            return search(treeNode.getRightNode(), value);
        }
    }

    /**
     * 通过值删除节点
     */
    public void delNodeByValue(int value) {
        if (treeNode == null) {
            return;
        }
        //通过值查询节点
        TreeNode targetNode = searchByValue(value);
        if (targetNode == null) {
            return;
        }
        if (treeNode.getLeftNode() == null && treeNode.getRightNode() == null) {
            return;
        }
        //查询当前节点的父节点
        TreeNode parent = searchParent(value);
        if (parent != null && targetNode.getLeftNode() == null && targetNode.getRightNode() == null) {
            if (parent.getLeftNode() != null && parent.getLeftNode().getValue() == value) {
                parent.setLeftNode(null);
            } else if (parent.getRightNode() != null && parent.getRightNode().getValue() == value) {
                parent.setRightNode(null);
            }
        }
    }

    /**
     * 查询当前节点的父节点
     */
    private TreeNode searchParent(int value) {
        if (treeNode == null) {
            return null;
        } else {
            return searchParent(treeNode, value);
        }
    }

    /**
     * 查询父节点
     */
    private TreeNode searchParent(TreeNode treeNode, int value) {
        if ((treeNode.getLeftNode() != null && treeNode.getLeftNode().getValue() == value) ||
                (treeNode.getRightNode() != null && treeNode.getRightNode().getValue() == value)) {
            return treeNode;
        } else {
            if (value < treeNode.getValue() && treeNode.getLeftNode() != null) {
                return searchParent(treeNode.getLeftNode(), value);
            } else if (value >= treeNode.getValue() && treeNode.getRightNode() != null) {
                return searchParent(treeNode.getRightNode(), value);
            } else {
                return null;
            }
        }
    }

    /**
     * 添加节点
     */
    public void add(TreeNode treeNode) {
        if (this.treeNode == null) {
            this.treeNode = treeNode;
        } else {
            add(this.treeNode, treeNode);
        }
    }

    private void add(TreeNode mainTreeNode, TreeNode treeNode){
        if (treeNode.getValue() < mainTreeNode.getValue()) {
            if (mainTreeNode.getLeftNode() == null) {
                mainTreeNode.setLeftNode(treeNode);
            } else {
                add(mainTreeNode.getLeftNode(), treeNode);
            }
        } else {
            if (mainTreeNode.getRightNode() == null) {
                mainTreeNode.setRightNode(treeNode);
            } else {
                add(mainTreeNode.getRightNode(), treeNode);
            }
        }
        //左旋转
        if(getRightHeight(mainTreeNode) - getLeftHeight(mainTreeNode) > 1) {
            int leftHeight = getLeftHeight(mainTreeNode.getRightNode());
            int rightHeight = getRightHeight(mainTreeNode.getRightNode());
            if(mainTreeNode.getRightNode() != null && leftHeight > rightHeight) {
                rightRotate(mainTreeNode.getRightNode());
                leftRotate(mainTreeNode.getRightNode());
            } else {
                leftRotate(mainTreeNode.getRightNode());
            }
            return ;
        }

        //右旋转
        if(getLeftHeight(mainTreeNode) - getRightHeight(mainTreeNode) > 1) {
            int leftHeight = getLeftHeight(mainTreeNode.getLeftNode());
            int rightHeight = getRightHeight(mainTreeNode.getLeftNode());
            if(mainTreeNode.getLeftNode() != null && rightHeight > leftHeight) {
                leftRotate(mainTreeNode.getLeftNode());
                rightRotate(mainTreeNode);
            } else {
                rightRotate(mainTreeNode);
            }
        }
    }

    /**
     * 当前树的高度
     */
    public int height() {
        int leftHeight = treeNode.getLeftNode() == null ? 0 : height(treeNode.getLeftNode());
        int rightHeight = treeNode.getRightNode() == null ? 0 : height(treeNode.getRightNode());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 当前节点的高度
     */
    private int height(TreeNode treeNode) {
        int leftHeight = treeNode == null || treeNode.getLeftNode() == null ? 0 : height(treeNode.getLeftNode());
        int rightHeight = treeNode == null || treeNode.getRightNode() == null ? 0 : height(treeNode.getRightNode());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 获取左子树的高度
     */
    private int getLeftHeight(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        return height(treeNode.getLeftNode());
    }

    /**
     * 获取右子树的高度
     */
    private int getRightHeight(TreeNode treeNode) {
        if (treeNode.getRightNode() == null) {
            return 0;
        }
        return height(treeNode.getRightNode());
    }

    /**
     * 左旋
     */
    private void leftRotate(TreeNode treeNode) {
        TreeNode newNode = new TreeNode(treeNode.getValue());
        newNode.setLeftNode(treeNode.getLeftNode());
        newNode.setRightNode(treeNode.getRightNode().getLeftNode());
        treeNode.setValue(treeNode.getRightNode().getValue());
        treeNode.setRightNode(treeNode.getRightNode().getRightNode());
        treeNode.setLeftNode(newNode);
    }

    /**
     * 右旋
     */
    private void rightRotate(TreeNode treeNode) {
        TreeNode newNode = new TreeNode(treeNode.getValue());
        newNode.setRightNode(treeNode.getRightNode());
        newNode.setLeftNode(treeNode.getLeftNode().getRightNode());
        treeNode.setValue(treeNode.getLeftNode().getValue());
        treeNode.setLeftNode(treeNode.getLeftNode().getLeftNode());
        treeNode.setRightNode(newNode);
    }

    //中序遍历
    public void infixOrder() {
        if (treeNode != null) {
            infixOrder(treeNode);
        } else {
            System.out.println("树为空, 遍历不出来");
        }
    }

    /**
     * 中序遍历
     */
    private void infixOrder(TreeNode treeNode) {
        if (treeNode.getLeftNode() != null) {
            infixOrder(treeNode.getLeftNode());
        }
        System.out.println(treeNode.getValue());
        if (treeNode.getRightNode() != null) {
            infixOrder(treeNode.getRightNode());
        }
    }
}