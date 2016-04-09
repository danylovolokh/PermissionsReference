package com.volokh.danylo.permissionsreferencelist.adapter.permission_items;

import com.volokh.danylo.permissionsreferencelist.R;
import com.volokh.danylo.permissionsreferencelist.adapter.PermissionItemViewHolder;
import com.volokh.danylo.permissionsreferencelist.adapter.visitors.PermissionItemVisitor;

/**
 * Created by danylo.volokh on 4/7/16.
 *
 * This Model contains all the data related ot the UI representation of the method item
 */
public abstract class MethodItem implements PermissionListItem {

    private final String mName;

    protected MethodItem(String name) {
        mName = name;
    }

    /**
     * This method should be implemented and perform
     * operation that demonstrates how calling of this method can cause {@link SecurityException}
     */
    public abstract void demonstrate();

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public int getLayoutId() {
        return R.layout.method_item;
    }

    @Override
    public void accept(PermissionItemViewHolder holder, PermissionItemVisitor initializeItemVisitor) {
        initializeItemVisitor.visit(holder, this);
    }

}
