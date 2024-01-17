package com.example.demo.utilties;

import java.util.HashMap;
import java.util.Map;

public class LabelUtility {

	public static Map<String, String> getLabelsForUserExportFile(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "name");
		map.put("role", "role");
		map.put("password","password");
		return map;
	}
}
