package cn.zifangsky.common;

public class PageInfo {
	private Long count;
	private Long from;
	private Long to;
	private Long perSize;
	private Long currentPage;
	private String sortName;
	private String sortOrder;

	public PageInfo() {

	}

	public PageInfo(Long count, Long from, Long to, Long perSize, Long currentPage, String sortName, String sortOrder) {
		this.count = count;
		this.from = from;
		this.to = to;
		this.perSize = perSize;
		this.currentPage = currentPage;
		this.sortName = sortName;
		this.sortOrder = sortOrder;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getFrom() {
		return from;
	}

	public void setFrom(Long from) {
		this.from = from;
	}

	public Long getTo() {
		return to;
	}

	public void setTo(Long to) {
		this.to = to;
	}

	public Long getPerSize() {
		return perSize;
	}

	public void setPerSize(Long perSize) {
		this.perSize = perSize;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
}
