package com.BlogApp1.PayLoad;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

	
private List<PostDto>content;
private int pageNo;
private int pageSize;
private long totalElements;
private int totalPage;
private boolean last;
public List<PostDto> getContent() {
	return content;
}
public void setContent(List<PostDto> content) {
	this.content = content;
}
public int getPageNo() {
	return pageNo;
}
public void setPageNo(int pageNo) {
	this.pageNo = pageNo;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}
public long getTotalElements() {
	return totalElements;
}
public void setTotalElements(long totalElements) {
	this.totalElements = totalElements;
}
public int getTotalPage() {
	return totalPage;
}
public void setTotalPage(int totalPage) {
	this.totalPage = totalPage;
}
public boolean isLast() {
	return last;
}
public void setLast(boolean last) {
	this.last = last;
}

}
