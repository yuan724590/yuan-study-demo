package yuan.study.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yuan.study.demo.entity.TreeNode;
import yuan.study.demo.service.TreeService;

@Slf4j
@Service
public class TreeServiceImpl implements TreeService {

    @Override
    public void binaryFindTree(){
        TreeNode treeNode = new TreeNode(0, null, null);
        //增加二叉查找树的节点
        insertBinaryFindTreeNode(treeNode, -5);
        insertBinaryFindTreeNode(treeNode, -2);
        insertBinaryFindTreeNode(treeNode, 1);
        insertBinaryFindTreeNode(treeNode, 2);
        insertBinaryFindTreeNode(treeNode, 3);
        //前序遍历
        preambleBinaryFindTree(treeNode);
        System.out.print("\n");
        //中序遍历
        middleOrderBinaryFindTree(treeNode);
        System.out.print("\n");
        //后序遍历
        postOrderBinaryFindTree(treeNode);
        //查找二叉树中键值为value的节点
        searchTreeNode(treeNode, 1);
    }

    /**
     * 查找二叉树中键值为value的节点
     */
    private TreeNode searchTreeNode(TreeNode treeNode, Integer value) {
        if (treeNode == null)
            return null;
        if (treeNode.getValue() > value)
            return searchTreeNode(treeNode.getLeftNode(), value);
        else if (treeNode.getValue() < value)
            return searchTreeNode(treeNode.getRightNode(), value);
        else
            return treeNode;
    }

    /**
     * 前序遍历
     */
    private void preambleBinaryFindTree(TreeNode treeNode){
        if(treeNode != null){
            System.out.print(treeNode.getValue() + " ");
            preambleBinaryFindTree(treeNode.getLeftNode());
            preambleBinaryFindTree(treeNode.getRightNode());
        }
    }

    /**
     * 中序遍历
     */
    private void middleOrderBinaryFindTree(TreeNode treeNode){
        if(treeNode != null){
            middleOrderBinaryFindTree(treeNode.getLeftNode());
            System.out.print(treeNode.getValue() + " ");
            middleOrderBinaryFindTree(treeNode.getRightNode());
        }
    }

    /**
     * 右序遍历
     */
    private void postOrderBinaryFindTree(TreeNode treeNode){
        if(treeNode != null){
            postOrderBinaryFindTree(treeNode.getLeftNode());
            postOrderBinaryFindTree(treeNode.getRightNode());
            System.out.print(treeNode.getValue() + " ");
        }
    }

    /**
     * 增加二叉查找树的节点
     */
    private void insertBinaryFindTreeNode(TreeNode treeNode, int value){
        //找到插入节点的位置
        TreeNode parent;
        TreeNode current = treeNode;
        while(true){
            parent = current;
            if(current.getValue() == value){
                return;
            }
            if(value > current.getValue()){
                current = current.getRightNode();
                if(current == null){
                    parent.setRightNode(new TreeNode(value, null, null));
                    return;
                }
            }else{
                current = current.getLeftNode();
                if(current == null){
                    parent.setLeftNode(new TreeNode(value, null, null));
                    return;
                }
            }
        }
    }
}
