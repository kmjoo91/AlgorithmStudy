package com.naver.tree.balancedsearchtree;

import com.naver.tree.balancedsearchtree.model.Node;

/**
 * Created by AD on 2017-09-01.
 */
public interface BTree {
	public Node search(int key);
	public boolean addData(Node data);
	public void removeData(Node data);
	public void show();
}
