package com.nexusy.virgo.android.adapter;

import java.util.List;
import java.util.Map;

import com.nexusy.virgo.android.R;
import com.nexusy.virgo.android.model.BillItemType;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author lan
 * @since 2014-3-12
 */
public class BillAdapter extends SimpleExpandableListAdapter {

    private Context context;
    private List<? extends List<? extends Map<String, ?>>> childData;

    public BillAdapter(Context context, List<? extends Map<String, ?>> groupData, int groupLayout, String[] groupFrom,
            int[] groupTo, List<? extends List<? extends Map<String, ?>>> childData, int childLayout,
            String[] childFrom, int[] childTo) {
        super(context, groupData, groupLayout, groupFrom, groupTo, childData, childLayout, childFrom, childTo);
        this.context = context;
        this.childData = childData;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
            ViewGroup parent) {
        View view = super.getChildView(groupPosition, childPosition, isLastChild, convertView, parent);
        final Map<String, ?> map = childData.get(groupPosition).get(childPosition);
        BillItemType type = (BillItemType) map.get("type");
        TextView tv = (TextView) view.findViewById(R.id.bill_money);
        if (type == BillItemType.INCOME) {
            tv.setTextColor(context.getResources().getColor(R.color.holo_green_dark));
        } else {
            tv.setTextColor(context.getResources().getColor(R.color.holo_red_dark));
        }
        
        Button del = (Button) view.findViewById(R.id.del);
        del.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Toast.makeText(context, map.get("id").toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
