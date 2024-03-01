package leecode;

import model.TreeNode;

/**
 * 检查是否是平衡树
 */
public class BalanceTree {

    public boolean isBalanceTree(TreeNode node){
        //检查是否是个空数
        if (node == null)
            return false;
        //检查是否是左平衡
        if (!isBalanceTree(node.left))
            return false;
        //检查是否是右平衡
        if (!isBalanceTree(node.right))
            return false;
        //计算左子树的深度
        int l = getDepth(node.left);
        //计算右子数的深度
        int r = getDepth(node.right);
        //左右子树深度大于1
        return Math.abs(l - r) <= 1;
    }

    /**
     * 计算树深度 返回最大深度
     * @param node
     * @return
     */
    public int getDepth(TreeNode node){

        if (node == null)
            return 0;
        int l = getDepth(node.left) + 1;
        int r = getDepth(node.right) + 1;
        return Math.max(r, l);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(args.length);
        BalanceTree balanceTree = new BalanceTree();
        balanceTree.isBalanceTree(treeNode);
    }

}
