package com.example.dc.refrigeratorproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.resposeBean.CommentRes;
import com.example.dc.refrigeratorproject.util.InputUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DC on 2019/5/12.
 */

public class CommentExpandAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "CommentExpandAdapter";
    private List<CommentRes> commentBeanList = new ArrayList<> ();
    private Context context;

    public CommentExpandAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<CommentRes> list) {
        commentBeanList = list;
        notifyDataSetChanged ();;
    }


    @Override
    public int getGroupCount() {
        return commentBeanList.size ();
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return commentBeanList.get (i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getCombinedChildId (groupPosition, childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    boolean isLike = false;

    @Override
    public View getGroupView(final int groupPosition, boolean isExpand, View convertView, ViewGroup viewGroup) {
        final GroupHolder groupHolder;
        CommentRes commentRes = commentBeanList.get (groupPosition);

        if (convertView == null) {
            convertView = LayoutInflater.from (context).inflate (R.layout.comment_item_layout, viewGroup, false);
            groupHolder = new GroupHolder (convertView);
            convertView.setTag (groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag ();
        }

        groupHolder.tv_name.setText (commentRes.getUserName ());
        String time = InputUtils.getDateToString (commentRes.getCreateTime (),"yyyy-MM-dd HH:mm:ss");
        groupHolder.tv_time.setText (time);
        groupHolder.tv_content.setText (commentRes.getContent ());
        groupHolder.iv_like.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (isLike) {
                    isLike = false;
                    groupHolder.iv_like.setColorFilter (Color.parseColor ("#aaaaaa"));
                } else {
                    isLike = true;
                    groupHolder.iv_like.setColorFilter (Color.parseColor ("#FF5C5C"));
                }
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        return null;
    }

    public void addTheCommentData(CommentRes commentDetailBean) {
        if (commentDetailBean != null) {

            commentBeanList.add (commentDetailBean);
            notifyDataSetChanged ();
        } else {
            throw new IllegalArgumentException ("评论数据为空!");
        }

    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private class GroupHolder {
        private SimpleDraweeView logo;
        private TextView tv_name, tv_content, tv_time;
        private ImageView iv_like;

        public GroupHolder(View view) {
            logo = view.findViewById (R.id.comment_item_logo);
            tv_content = view.findViewById (R.id.comment_item_content);
            tv_name = view.findViewById (R.id.comment_item_userName);
            tv_time = view.findViewById (R.id.comment_item_time);
            iv_like = view.findViewById (R.id.comment_item_like);
        }
    }

    private class ChildHolder {
        private TextView tv_name, tv_content;

        public ChildHolder(View view) {
            tv_name = (TextView) view.findViewById (R.id.reply_item_user);
            tv_content = (TextView) view.findViewById (R.id.reply_item_content);
        }
    }
}

