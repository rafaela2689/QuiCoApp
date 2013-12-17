/*
 * Copyright (c) 2012 Samsung Electronics Co., Ltd.
 * All rights reserved.
 *
 * This software is a confidential and proprietary information of Samsung
 * Electronics, Inc. ("Confidential Information").  You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Samsung Electronics.
 */
package com.android.quiz.util;

/**
 * Provides the type font text for application
 */
public enum TypeFont {

	MUSEO_SLAB;

	private static final String TXT_MUSEO_SLAB = "Museo_Slab_0";

	public String fontName() {
		switch (this) {
		case MUSEO_SLAB:
			return TXT_MUSEO_SLAB;
		default:
			return null;
		}
	}

}
