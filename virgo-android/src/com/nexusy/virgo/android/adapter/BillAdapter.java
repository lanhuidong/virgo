package com.nexusy.virgo.android.adapter;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.nexusy.virgo.android.R;
import com.nexusy.virgo.android.http.UrlConstants;
import com.nexusy.virgo.android.http.VirgoHttpClient;
import com.nexusy.virgo.android.model.BillItemType;

/**
 * @author lan
 * @since 2014-3-12
 */
public class BillAdapter extends SimpleExpandableListAdapter {

    private Context context;
    private List<Map<String, Object>> groupData;
    private List<List<Map<String, Object>>> childData;

    public BillAdapter(Context context, List<Map<String, Object>> groupData, int groupLayout, String[] groupFrom,
            int[] groupTo, List<List<Map<String, Object>>> childData, int childLayout, String[] childFrom, int[] childTo) {
        super(context, groupData, groupLayout, groupFrom, groupTo, childData, childLayout, childFrom, childTo);
        this.context = context;
        this.groupData = groupData;
        this.childData = childData;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView,
            ViewGroup parent) {
        View view = super.getChildView(groupPosition, childPosition, isLastChild, convertView, parent);
        final Map<String, Object> map = childData.get(groupPosition).get(childPosition);
        final BillItemType type = (BillItemType) map.get("type");
        TextView tv = (TextView) view.findViewById(R.id.bill_money);
        if (type == BillItemType.INCOME) {
            tv.setTextColor(context.getResources().getColor(R.color.holo_green_dark));
        } else {
            tv.setTextColor(context.getResources().getColor(R.color.holo_red_dark));
        }

        final Long id = (Long) map.get("id");

        Button del = (Button) view.findViewById(R.id.del);
        del.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new BillDeleteTask(groupPosition, childPosition, map).execute(id);
            }
        });
        return view;
    }

    private class BillDeleteTask extends AsyncTask<Long, Void, Boolean> {

        private int groupPosition;
        private int childPosition;
        private Map<String, Object> billItem;

        public BillDeleteTask(int groupPosition, int childPosition, Map<String, Object> billItem) {
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
            this.billItem = billItem;
        }

        @Override
        protected Boolean doInBackground(Long... params) {
            String result = VirgoHttpClient.parseHttpResponseToString(VirgoHttpClient.post(UrlConstants.DEL_BILL_URL+params[0], new HashMap<String, String>(0)));
            return Boolean.valueOf(result);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                childData.get(groupPosition).remove(childPosition);
                Map<String, Object> groupMap = groupData.get(groupPosition);
                NumberFormat nf = NumberFormat.getCurrencyInstance();
                BillItemType type = (BillItemType) billItem.get("type");
                if (type == BillItemType.INCOME) {
                    try {
                        groupMap.put(
                                "income",
                                nf.format(Double.valueOf(((String) groupMap.get("income")).substring(1))
                                        - Double.valueOf(((String) billItem.get("money")).substring(1))));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    try {
                        groupMap.put(
                                "pay",
                                nf.format(Double.valueOf(((String) groupMap.get("pay")).substring(1))
                                        - Double.valueOf(((String) billItem.get("money")).substring(1))));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                groupData.set(groupPosition, groupMap);

                BillAdapter.this.notifyDataSetChanged();
            }
        }

    }

}
