package com.example.CityBus.database;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.HashMap;

public class BusModel extends SugarRecord<BusModel> {

    public String busName;
    public String routeStart;
    public String routeEnd;
    public HashMap<String,String> routeMap;
}
