package com.volokh.danylo.permissionsreferencelist.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volokh.danylo.permissionsreferencelist.R;
import com.volokh.danylo.permissionsreferencelist.adapter.permission_items.MethodItem;
import com.volokh.danylo.permissionsreferencelist.adapter.permission_items.header_like_items.PermissionListHeaderItem;
import com.volokh.danylo.permissionsreferencelist.adapter.permission_items.PermissionListItem;
import com.volokh.danylo.permissionsreferencelist.adapter.visitors.PermissionItemVisitor;

import java.util.List;

/**
 * Created by danylo.volokh on 4/6/16.
 */
public class PermissionsReferenceAdapter extends RecyclerView.Adapter<PermissionItemViewHolder> {

    private static final int VIEW_TYPE_PERMISSION_GROUP_NAME = 0;
    private static final int VIEW_TYPE_PERMISSION_NAME = 1;
    private static final int VIEW_TYPE_METHOD_ITEM = 2;

    private static final String TAG = PermissionsReferenceAdapter.class.getSimpleName();

    private final List<PermissionListItem> mPermissionsList;

    public PermissionsReferenceAdapter(List<PermissionListItem> permissionsList){
        mPermissionsList = permissionsList;
    }

    @Override
    public PermissionItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutId = -1;
        switch (viewType){
            case VIEW_TYPE_PERMISSION_GROUP_NAME:
                layoutId = R.layout.permission_group_name;
                break;
            case VIEW_TYPE_PERMISSION_NAME:
                layoutId = R.layout.permission_name;
                break;
            case VIEW_TYPE_METHOD_ITEM:
                layoutId = R.layout.method_item;
                break;
        }

        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new PermissionItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PermissionItemViewHolder holder, int position) {
        PermissionListItem permissionListItem = mPermissionsList.get(position);
        permissionListItem.accept(holder, initializeItemVisitor);
        holder.name.setText(permissionListItem.getName());
    }

    @Override
    public int getItemViewType(int position) {
        PermissionListItem item = mPermissionsList.get(position);
        int layoutId = item.getLayoutId();
        switch (layoutId){
            case R.layout.permission_group_name:
                return VIEW_TYPE_PERMISSION_GROUP_NAME;
            case R.layout.permission_name:
                return VIEW_TYPE_PERMISSION_NAME;
            case R.layout.method_item:
                return VIEW_TYPE_METHOD_ITEM;
        }
        throw new RuntimeException("unhandled item view type");
    }

    @Override
    public int getItemCount() {
        return mPermissionsList.size();
    }

    private final PermissionItemVisitor initializeItemVisitor = new PermissionItemVisitor() {
        @Override
        public void visit(PermissionItemViewHolder holder, PermissionListHeaderItem headerItem) {
            Log.v(TAG, "visit PermissionListHeaderItem");
        }

        @Override
        public void visit(PermissionItemViewHolder holder, final MethodItem methodItem) {
            Log.v(TAG, "visit MethodItem");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     methodItem.demonstrate();
                }
            });
        }

    };
}
