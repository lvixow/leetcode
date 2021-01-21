package com.jianzhi_offer;

import com.jianzhi_offer.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * author:w_liangwei
 * date:2021/1/21
 * Description: offer 37
 */
public class Codec {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1, 2, 3);
        node1.right.setLiftRight(4, 5);
        System.out.println(node1);
        String serialize = serialize(node1);
        System.out.println(serialize);

        TreeNode deserialize = deserialize("[]");
        System.out.println(deserialize);
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            //这样只会添加叶子节点的左右null节点，而再往下的null则不会被添加
            if (poll != null) {
                sb.append(poll.val).append(",");
                queue.offer(poll.left);
                queue.offer(poll.right);
            } else {
                sb.append("null").append(",");
            }
        }
        sb.deleteCharAt(sb.length() -1);
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data.isEmpty() || data.length() <= 2) return null;
        String[] split = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
        //队列中始终放的是最下边要挂载节点的那层
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        //用于指示当前待挂载到树中的节点，挂载一个节点的左右节点，会使index+2
        int index = 1;
        //队列加入的是每层要构建的节点，每执行一次循环就会对一个节点进行左右节点的挂载
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            //如果当前节点的左节点不为null则挂载左节点
            if (!"null".equals(split[index])) {
                //挂载左节点
                poll.left = new TreeNode(Integer.parseInt(split[index]));
                //将左节点加入下一层待构建节点
                queue.offer(poll.left);
            }
            index++;
            //当前节点的右节点不为null则挂载右节点
            if (!"null".equals(split[index])) {
                //挂载右节点
                poll.right = new TreeNode(Integer.parseInt(split[index]));
                //将右节点加入下一层待构建节点
                queue.offer(poll.right);
            }
            index++;
        }
        return root;
    }
}
