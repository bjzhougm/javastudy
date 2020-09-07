package leecode;

import model.TreeNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


/**
 * 广度优先和深度优先
 */
public class TreeTraversal {

    public List<Integer> bfs(TreeNode root){
        //定义一个队列queue 放treenode linked list<treenode>
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        //定义一个list放返回的数据
        List<Integer> list = new LinkedList<Integer>();
        //如果root为空则直接返回list
        if (root == null)
            return list;
        //把root放到队列里面
        queue.add(root);
        //判断队列是否为空，如果不为空
        while (!queue.isEmpty()){
            /**
             * 从队列中获取节点
             * 如果左节点不为空，则把左节点到队列中，
             * 如果右节点不为空，则把右节点放队列中，
             * 把节点的值放到list中
             * 开始循环，remove的时候是从左到右一次出队列
             * 返回list值
             */
            TreeNode t = queue.remove();
            if (t.left != null)
                queue.add(t.left);
            if (t.right!= null)
                queue.add(t.right);
            list.add(t.val);
        }
        return list;
    }

    /**
     * @param root
     */
    public List<Integer> dfs(TreeNode root){
        //定义一个tree node 栈
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //定义一个返回结果的list
        List<Integer> list = new LinkedList<Integer>();
        //如果为空 直接返回空的list,否则把跟节点压入到栈
        if (root == null )
            return list;
        stack.push(root);
        /**
         * 如果不为空，则把根节点弹出放到list中，
         * 根据栈的特点后进先出，先判断右节点，把右节点压入栈中
         * 再判断左节点，再把左节点压入栈中
         */
        while (!stack.isEmpty()){
            TreeNode t = stack.pop();
            if(t.right!= null)
                stack.push(t.right);
            if (t.left != null)
                stack.push(t.left);
            list.add(t.val);
        }
        return list;
    }
}
