package com.hitpoint.surveypark.model;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = 8899160647957785251L;

	public abstract Integer getId() ;

	public abstract void setId(Integer id) ;
	
}
