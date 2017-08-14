/**
 * 
 */
package com.manage.adapter;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.manage.action.BaseAction;
import com.manage.po.Ordered;
import com.manage.po.Remark;
import com.manage.util.DateFormat;

/**
 * @author 情愿孤独
 *
 */
public class RemarkAdapter extends TypeAdapter<Remark> {

	@Override
	public Remark read(JsonReader in) throws IOException {
		// TODO Auto-generated method stub
		final Remark remark = new Remark();
		
		in.beginObject();
		while(in.hasNext()){
			switch (in.nextName()) {
			case "remarkId":
				remark.setRemarkId(in.nextString());
				break;
			case "ordered":
				remark.setOrdered(BaseAction.Json2Object(in.nextString(), Ordered.class));
				break;
			case "level":
				remark.setLevel((short) in.nextInt());
				break;
			case "content":
				remark.setContent(in.nextString());
				break;
			case "sendId":
				remark.setSendId(in.nextString());
				break;
			case "receId":
				remark.setReceId(in.nextString());
				break;
			case "addTime":
				remark.setAddTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			default:
				break;
			}
		}
		in.endObject();
		return remark;
	}

	@Override
	public void write(JsonWriter out, Remark remark) throws IOException {
		// TODO Auto-generated method stub
		out.beginObject();
			out.name("remarkId").value(remark.getRemarkId());
			out.name("ordered").value(BaseAction.Object2json(remark.getOrdered()));
			out.name("level").value(remark.getLevel());
			out.name("content").value(remark.getContent());
			out.name("sendId").value(remark.getSendId());
			out.name("receId").value(remark.getReceId());
			out.name("addTime").value(DateFormat.getTimestampToString(remark.getAddTime()));
		out.endObject();
	}

}
