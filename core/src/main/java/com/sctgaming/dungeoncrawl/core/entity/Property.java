package com.sctgaming.dungeoncrawl.core.entity;

import java.io.Serializable;

public class Property<T extends Serializable> {
	private final String key;
	private final Class<T> vClass;
	private final T defValue;

	/**
	 * Creates a new Property Key with the specified key, class, and default value.<br>
	 * The property key should be unique, otherwise you will overwrite other properties that exist.<br>
	 * A list of properties is maintained in {@link Properties}.<br>
	 * 
	 * @param key {@link String} unique to this property
	 * @param valueClass the class of the value, must be the same as the type you declare to the generic
	 * @param defValue the default value to return if the property is missing
	 */
	public Property(String key, Class<T> valueClass, T defValue) {
		this.key = key;
		this.vClass = valueClass;
		this.defValue = defValue;
	}

	/**
	 * Creates a new Property Key with the specified key, class, and a default value of null.<br>
	 * The property key should be unique, otherwise you will overwrite other properties that exist.<br>
	 * A list of properties is maintained in {@link Properties}.<br>
	 * 
	 * @param key {@link String} unique to this property
	 * @param valueClass the class of the value, must be the same as the type you declare to the generic
	 */
	public Property(String key, Class<T> valueClass) {
		this(key, valueClass, null);
	}

	public String getKey() {
		return key;
	}

	public Class<T> getVClass() {
		return vClass;
	}

	public T getDefValue() {
		return defValue;
	}
}
