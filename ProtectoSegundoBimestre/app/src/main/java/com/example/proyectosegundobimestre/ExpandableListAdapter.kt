package com.example.proyectosegundobimestre

import android.view.LayoutInflater
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import java.util.HashMap

class ExpandableListAdapter(var context: Context, var header:MutableList<String>, var body:MutableList<String>) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return header.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return  1
    }

    override fun getGroup(groupPosition: Int): String {
        return header[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        return body[groupPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        if(convertView == null){
            val inflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_group,null)
        }
        val title = convertView?.findViewById<TextView>(R.id.tv_exapandable_tittle)
        title?.text = getGroup(groupPosition)
        return convertView!!
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChils: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if(convertView == null){
            val inflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_child,null)
        }
        val title = convertView?.findViewById<TextView>(R.id.tv_exapandable_content)
        title?.text = getChild(groupPosition,childPosition)
        return convertView!!
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}