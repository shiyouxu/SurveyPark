package com.hitpoint.surveypark.model;

public class Question {
	private Integer id;
	//����0~8
	private int questionType;
	
	private String title;
	//ѡ��
	private String options;
	//������
	private boolean other;
	//��������ʽ��0-�� 1-�ı��� 2-�����б�
	private int otherStyle;
	//����������ѡ��
	private String otherSelectOptions;
	//����ʽ�б��⼯
	private String matrixRowTitles;
	//����ʽ�б��⼯
	private String matrixColTitles;
	//����ʽ����ѡ�
	private String matrixSelectOptions;
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	//������Question��Page֮����һ������ϵ
	private Page page;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public boolean isOther() {
		return other;
	}
	public void setOther(boolean other) {
		this.other = other;
	}
	public int getOtherStyle() {
		return otherStyle;
	}
	public void setOtherStyle(int otherStyle) {
		this.otherStyle = otherStyle;
	}
	public String getOtherSelectOptions() {
		return otherSelectOptions;
	}
	public void setOtherSelectOptions(String otherSelectOptions) {
		this.otherSelectOptions = otherSelectOptions;
	}
	public String getMatrixRowTitles() {
		return matrixRowTitles;
	}
	public void setMatrixRowTitles(String matrixRowTitles) {
		this.matrixRowTitles = matrixRowTitles;
	}
	public String getMatrixColTitles() {
		return matrixColTitles;
	}
	public void setMatrixColTitles(String matrixColTitles) {
		this.matrixColTitles = matrixColTitles;
	}
	public String getMatrixSelectOptions() {
		return matrixSelectOptions;
	}
	public void setMatrixSelectOptions(String matrixSelectOptions) {
		this.matrixSelectOptions = matrixSelectOptions;
	}
	
	
}
