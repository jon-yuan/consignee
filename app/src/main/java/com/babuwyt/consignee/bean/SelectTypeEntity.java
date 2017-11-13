package com.babuwyt.consignee.bean;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by lenovo on 2017/11/13.
 */

public class SelectTypeEntity implements IPickerViewData {
    private String str;
    private int type;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String getPickerViewText() {
        return str;
    }
}
