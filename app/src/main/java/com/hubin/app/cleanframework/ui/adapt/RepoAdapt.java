package com.hubin.app.cleanframework.ui.adapt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hubin.app.cleanframework.R;
import com.hubin.app.scm.models.GitHubRepo;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by hubin on 2017/5/20.
 */

public class RepoAdapt extends RecyclerView.Adapter<RepoAdapt.RepoViewHolder> {

    List<GitHubRepo> repoList;
    Context mContext;

    public RepoAdapt(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.content_main, parent, false);
        return new RepoViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        if (repoList == null || repoList.size() <= position)
        {
            Log.i("RepoAdapt","onBindViewHolder: null");
            return;
        }
        final GitHubRepo item = repoList.get(position);
        Log.i("RepoAdapt","onBindViewHolder:"+ item.getName());
        if (item == null)
        {
            return;
        }

        try {
            holder.nameView.setText(item.getName());
            holder.idView.setText(item.getId()+"");
        } catch (Exception e) {
        }
    }

    @Override
    public int getItemCount() {
        if (repoList != null) {
            return repoList.size();
        }
        return 0;
    }

    class RepoViewHolder extends RecyclerView.ViewHolder
    {
        //        ImageView iconView;
        TextView nameView;
        TextView idView;

        public RepoViewHolder(View itemView)
        {
            super(itemView);
//            iconView = (ImageView) itemView.findViewById(R.id.caller_icon);
            idView = (TextView) itemView.findViewById(R.id.repo_id);
            nameView = (TextView) itemView.findViewById(R.id.repo_name);
        }
    }

    public void initListItems(List<GitHubRepo> repoList)
    {
        Log.d(TAG, "initListItems: ");
        this.repoList = repoList;
        notifyDataSetChanged();
    }
}
