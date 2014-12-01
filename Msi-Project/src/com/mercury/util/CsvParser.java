package com.mercury.util;

import java.util.*;

public class CsvParser {
	public static List<String> parse(String content){
		ArrayList<String> res = new ArrayList<String>();
		char deli = ',';
		char quote = '"';
		StringBuffer sb = new StringBuffer();
		boolean inquotes = false;
		for(char c:content.toCharArray()){
			if(inquotes){
				if(c == quote){
					//res.add(sb.toString());
					//sb.delete(0, sb.length());
					inquotes = false;
				}else sb.append(c);
			} else {
				if(c == deli){
					res.add(sb.toString());
					sb.delete(0, sb.length());
				}
				else if (c == quote)
					inquotes = true;
				else sb.append(c);
			}
		}
		if(sb.length() != 0) 
			res.add(sb.toString());
		return res;
	}
}
