package com.nexusy.virgo.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.nexusy.virgo.android.model.Bill;
import com.nexusy.virgo.android.model.BillItem;

public class BillOneDayActivity extends Activity {

    private Bill bill;
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    private SimpleAdapter adapter;

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_one_day);
        bill = (Bill) getIntent().getSerializableExtra("bill");
        setTitle(String.format("%1$tF", bill.getDate()));

        lv = (ListView) findViewById(R.id.bill);

        Map<String, Object> header = new HashMap<String, Object>();
        header.put("item", "名目");
        header.put("money", "金额");
        header.put("op", "操作");
        list.add(header);
        for (BillItem item : bill.getItems()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("item", item.getItem());
            map.put("money", item.getMoney());
            list.add(map);
        }
        adapter = new SimpleAdapter(BillOneDayActivity.this, list, R.layout.bill,
                new String[] { "item", "money", "op" }, new int[] { R.id.bill_item, R.id.bill_money, R.id.bill_del });
        lv.setAdapter(adapter);
    }

}
